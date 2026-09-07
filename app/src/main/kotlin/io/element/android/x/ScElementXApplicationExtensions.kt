package io.element.android.x

import android.content.Context
import androidx.startup.Initializer
import chat.schildi.lib.preferences.LocalScPreferencesStore
import chat.schildi.lib.preferences.NotExactlyACompositionLocal
import io.element.android.libraries.architecture.bindings
import io.element.android.x.di.AppBindings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import timber.log.Timber

class ScInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val appBindings = context.bindings<AppBindings>()
        // We want to make sure that preferences are ready as soon as possible, but we don't want to block the main thread.
        // On slow devices, this prefetch (DataStore read) can be very slow.
        MainScope().launch(Dispatchers.IO) {
            val ts = System.currentTimeMillis()
            val scPreferenceStore = appBindings.scPreferencesStore()
            scPreferenceStore.prefetch()
            LocalScPreferencesStore = NotExactlyACompositionLocal(scPreferenceStore)
            Timber.d("Initialized SC dependencies in ${System.currentTimeMillis() - ts} ms")
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
