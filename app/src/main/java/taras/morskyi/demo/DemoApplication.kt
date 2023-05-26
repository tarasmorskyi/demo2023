package taras.morskyi.demo

import android.app.Application

class DemoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        DependencyInjection.init(this)
    }
}