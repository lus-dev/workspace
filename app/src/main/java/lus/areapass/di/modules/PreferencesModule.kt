package lus.areapass.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import lus.areapass.cache.UserPreferences
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object PreferencesModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideUserLocalStorage(@ApplicationContext appContext: Context) = UserPreferences(appContext)

}