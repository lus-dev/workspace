package lus.areapass.di.modules

import android.content.Context
import com.cocosw.favor.FavorAdapter
import dagger.Module
import dagger.Provides
import lus.areapass.cache.UserFieldStorage
import lus.areapass.cache.UserPreferences
import javax.inject.Singleton


@Module
object PreferencesModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideUserLocalStorage(appContext: Context): UserFieldStorage {
        return FavorAdapter.Builder(appContext).build().create(UserFieldStorage::class.java)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideUserPreferences(storage: UserFieldStorage): UserPreferences {
        return UserPreferences(storage)
    }

}