/*
 * Copyright (c) 2025 Element Creations Ltd.
 * Copyright 2023-2025 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-Element-Commercial.
 * Please see LICENSE files in the repository root for full details.
 */

package io.element.android.libraries.matrix.ui.media

import android.content.Context
import android.os.Build
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
import coil3.memory.MemoryCache
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.allowRgb565
import coil3.svg.SvgDecoder
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import io.element.android.libraries.androidutils.system.isLowRamDevice
import io.element.android.libraries.di.annotations.ApplicationContext
import io.element.android.libraries.matrix.api.media.MatrixMediaLoader
import okhttp3.OkHttpClient

interface ImageLoaderFactory {
    fun newImageLoader(): ImageLoader
    fun newImageLoader(matrixMediaLoader: MatrixMediaLoader): ImageLoader
}

@ContributesBinding(AppScope::class)
class DefaultImageLoaderFactory(
    @ApplicationContext private val context: Context,
    private val okHttpClient: () -> OkHttpClient,
) : ImageLoaderFactory {
    private val okHttpNetworkFetcherFactory = OkHttpNetworkFetcherFactory(
        callFactory = {
            // Use newBuilder, see https://coil-kt.github.io/coil/network/#using-a-custom-okhttpclient
            okHttpClient().newBuilder().build()
        }
    )

    override fun newImageLoader(): ImageLoader {
        return createBuilder(context)
            .components {
                add(okHttpNetworkFetcherFactory)
            }
            .build()
    }

    override fun newImageLoader(matrixMediaLoader: MatrixMediaLoader): ImageLoader {
        return createBuilder(context)
            .components {
                add(okHttpNetworkFetcherFactory)
                // Add svg support
                add(SvgDecoder.Factory())
                // Add gif support
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    add(AnimatedImageDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
                add(AvatarDataKeyer())
                add(MediaRequestDataKeyer())
                add(AvatarDataFetcherFactory(matrixMediaLoader))
                add(MediaRequestDataFetcherFactory(matrixMediaLoader))
            }
            .build()
    }

    private fun createBuilder(context: Context): ImageLoader.Builder {
        val isLowRamDevice = context.isLowRamDevice()
        return ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder()
                    // Set memory cache to 10% of available memory, or 5% if low RAM
                    .maxSizePercent(context, if (isLowRamDevice) 0.05 else 0.15)
                    .build()
            }
            .apply {
                if (isLowRamDevice) {
                    // Use RGB_565 for bitmaps to save memory on low RAM devices
                    allowRgb565(true)
                    // Disable bitmap pooling if memory is critical, though usually it helps.
                    // Coil 3 handles this automatically mostly, but we can be explicit if needed.
                }
            }
    }
}
