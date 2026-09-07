/*
 * Copyright (c) 2026 Element Creations Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-Element-Commercial.
 * Please see LICENSE files in the repository root for full details.
 */

package io.element.android.libraries.featureflag.impl

import android.content.Context
import dev.zacsweers.metro.Inject
import io.element.android.libraries.androidutils.system.isLowRamDevice
import io.element.android.libraries.di.annotations.ApplicationContext
import io.element.android.libraries.featureflag.api.Feature
import io.element.android.libraries.featureflag.api.FeatureFlags
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * A [FeatureFlagProvider] that disables memory-intensive features on low RAM devices.
 */
@Inject
class LowRamFeatureFlagProvider(
    @ApplicationContext private val context: Context,
) : FeatureFlagProvider {
    override val priority: Int = HIGH_PRIORITY + 1

    private val lowRamOverriddenFeatures = setOf(
        FeatureFlags.MessageSearch,
        FeatureFlags.AutomaticBackPagination,
        FeatureFlags.MultiAccount,
        FeatureFlags.Threads,
    )

    override fun isFeatureEnabledFlow(feature: Feature): Flow<Boolean> {
        return if (context.isLowRamDevice() && hasFeature(feature)) {
            flowOf(false)
        } else {
            // This should not happen if hasFeature is implemented correctly
            flowOf(true)
        }
    }

    override fun hasFeature(feature: Feature): Boolean {
        return context.isLowRamDevice() && feature in lowRamOverriddenFeatures
    }
}
