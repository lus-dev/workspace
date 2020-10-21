package lus.areapass.di.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import lus.areapass.cache.db.AppDatabase
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): RoomDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, AppDatabase::class.java.simpleName)
            .build()
    }

}