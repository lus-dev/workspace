package lus.areapass

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class AreaPassApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        enableDateTimeAPI()
    }

    private fun enableDateTimeAPI() {
        AndroidThreeTen.init(this)
    }

}