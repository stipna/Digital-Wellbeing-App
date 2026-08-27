package com.stephan.screenlock.ui.screens.onboarding

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.stephan.screenlock.data.local.AppDatabase
import com.stephan.screenlock.data.local.entity.BlockedAppEntity
import com.stephan.screenlock.data.repository.InstalledAppsRepository
import com.stephan.screenlock.data.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Laedt Apps lazy erst bei Erreichen von Screen 3. Schreibt ausgewaehlte
 * Packages beim Abschluss in BlockedAppDao, setzt onboardingCompleted = true.
 * Ruft NICHT mehr setTrialStartTimestampIfUnset auf — Trial-Start liegt
 * ausschliesslich in PremiumGate.kt. Siehe claude/project-setup.md.
 */
class OnboardingViewModel(application: Application) : AndroidViewModel(application) {

    private val blockedAppDao = AppDatabase.getInstance(application).blockedAppDao()
    private val settingsRepository = SettingsRepository(application)
    private val installedAppsRepository = InstalledAppsRepository(application)

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    fun onPageChanged(page: Int) {
        _uiState.value = _uiState.value.copy(currentPage = page)
        if (page == 2 && _uiState.value.installedApps.isEmpty()) {
            _uiState.value = _uiState.value.copy(installedApps = installedAppsRepository.getInstalledApps())
        }
    }

    fun onAppToggled(packageName: String, selected: Boolean) {
        val current = _uiState.value.selectedPackages
        _uiState.value = _uiState.value.copy(
            selectedPackages = if (selected) current + packageName else current - packageName
        )
    }

    fun onOnboardingCompleted() {
        viewModelScope.launch {
            _uiState.value.selectedPackages.forEach { packageName ->
                blockedAppDao.insert(BlockedAppEntity(packageName, System.currentTimeMillis()))
            }
            settingsRepository.setOnboardingCompleted(true)
        }
    }
}
