package com.stephan.screenlock.ui.screens.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.stephan.screenlock.data.local.AppDatabase
import com.stephan.screenlock.data.local.entity.AppLimit
import com.stephan.screenlock.data.repository.InstalledAppsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val appLimitDao = AppDatabase.getInstance(application).appLimitDao()
    private val installedAppsRepository = InstalledAppsRepository(application)

    private val installedApps = MutableStateFlow(installedAppsRepository.getInstalledApps())

    val uiState: StateFlow<SettingsUiState> = combine(
        installedApps,
        appLimitDao.getAll()
    ) { apps, limits -> SettingsUiState(installedApps = apps, limits = limits) }
        .stateIn(viewModelScope, kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000), SettingsUiState())

    fun onLimitChanged(packageName: String, minutes: Int) {
        viewModelScope.launch {
            if (minutes <= 0) {
                appLimitDao.delete(packageName)
            } else {
                appLimitDao.upsert(AppLimit(packageName, minutes))
            }
        }
    }
}
