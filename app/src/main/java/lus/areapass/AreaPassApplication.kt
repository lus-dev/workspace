package lus.areapass

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import lus.areapass.di.DaggerApplicationComponent
import lus.areapass.di.InjectorProvider


class AreaPassApplication : Application(), InjectorProvider {

    override val component by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        enableDateTimeAPI()
    }

    private fun enableDateTimeAPI() {
        AndroidThreeTen.init(this)
    }

}