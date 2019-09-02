package lus.areapass

import android.app.Application
import lus.areapass.di.DaggerApplicationComponent
import lus.areapass.di.InjectorProvider


class AreaPassApplication : Application(), InjectorProvider {

    override val component by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

}