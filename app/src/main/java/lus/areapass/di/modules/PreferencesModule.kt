package lus.areapass.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import lus.areapass.cache.UserPreferences
import javax.inject.Singleton


@Module
object PreferencesModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideUserLocalStorage(appContext: Context) = UserPreferences(appContext)

}