package com.stephan.screenlock.util

import android.content.Context
import android.provider.Settings
import android.view.accessibility.AccessibilityManager
import com.stephan.screenlock.service.AppLockAccessibilityService

object AccessibilityUtils {

    fun isAppLockServiceEnabled(context: Context): Boolean {
        val am = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        val enabledServices = am.getEnabledAccessibilityServiceList(
            android.accessibilityservice.AccessibilityServiceInfo.FEEDBACK_ALL_MASK
        )
        val isEnabledLive = enabledServices.any {
            it.resolveInfo.serviceInfo.packageName == context.packageName &&
                it.resolveInfo.serviceInfo.name == AppLockAccessibilityService::class.java.name
        }
        if (isEnabledLive) return true

        // Fallback: manche OEM-ROMs aktualisieren den Live-Zustand verzoegert.
        val flatSetting = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        ) ?: return false
        val expectedComponent = "${context.packageName}/${AppLockAccessibilityService::class.java.name}"
        return flatSetting.split(':').any { it.equals(expectedComponent, ignoreCase = true) }
    }
}
