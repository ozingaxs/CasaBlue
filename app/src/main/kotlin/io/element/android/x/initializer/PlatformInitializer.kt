/*
 * Copyright (c) 2025 Element Creations Ltd.
 * Copyright 2022-2025 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-Element-Commercial.
 * Please see LICENSE files in the repository root for full details.
 */

package io.element.android.x.initializer

import android.content.Context
import android.system.Os
import androidx.startup.Initializer
import io.element.android.features.rageshake.api.logs.createWriteToFilesConfiguration
import io.element.android.libraries.architecture.bindings
import io.element.android.libraries.featureflag.api.FeatureFlags
import io.element.android.libraries.matrix.api.tracing.TracingConfiguration
import io.element.android.x.di.AppBindings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber

private const val ELEMENT_X_TARGET = "elementx"

class PlatformInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val appBindings = context.bindings<AppBindings>()
        val tracingService = appBindings.tracingService()
        val platformService = appBindings.platformService()
        val bugReporter = appBindings.bugReporter()
        Timber.plant(tracingService.createTimberTree(ELEMENT_X_TARGET))

        // We want to initialize the platform as fast as possible, but we need some preferences.
        // On slow devices, runBlocking on DataStore can take seconds.
        // We launch the initialization in a separate coroutine to not block the main thread startup.
        MainScope().launch(Dispatchers.IO) {
            val preferencesStore = appBindings.preferencesStore()
            val featureFlagService = appBindings.featureFlagService()

            // Parallelize pref fetches
            val logLevelDeferred = async { preferencesStore.getTracingLogLevelFlow().first() }
            val writesToLogcatDeferred = async { featureFlagService.isFeatureEnabled(FeatureFlags.PrintLogsToLogcat) }
            val traceLogPacksDeferred = async { preferencesStore.getTracingLogPacksFlow().first() }

            val logLevel = logLevelDeferred.await()
            val writesToLogcat = writesToLogcatDeferred.await()
            val traceLogPacks = traceLogPacksDeferred.await()

            val tracingConfiguration = TracingConfiguration(
                writesToLogcat = writesToLogcat,
                writesToFilesConfiguration = bugReporter.createWriteToFilesConfiguration(),
                logLevel = logLevel,
                extraTargets = listOf(ELEMENT_X_TARGET),
                traceLogPacks = traceLogPacks,
                sdkSentryDsn = appBindings.sentrySdkDsn()?.value?.takeIf { it.isNotBlank() },
            )
            bugReporter.setCurrentTracingLogLevel(logLevel.name)
            platformService.init(tracingConfiguration)
        }
        // Also set env variable for rust back trace
        Os.setenv("RUST_BACKTRACE", "1", true)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = mutableListOf()
}
