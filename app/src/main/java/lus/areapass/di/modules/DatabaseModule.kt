package lus.areapass.di.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import lus.areapass.cache.db.AppDatabase
import javax.inject.Singleton


@Module
object DatabaseModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideDatabase(appContext: Context): RoomDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, AppDatabase::class.java.simpleName).build()
    }

}