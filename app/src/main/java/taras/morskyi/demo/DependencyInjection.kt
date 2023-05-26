package taras.morskyi.demo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import taras.morskyi.base.di.baseModule
import taras.morskyi.feature.di.featureModule
import taras.morskyi.base_kmm.di.baseKmmModule
import taras.morskyi.feature_kmm.di.featureKmmModule

object DependencyInjection {

    fun init(application: Application) {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(application)
            modules(
                baseModule,
                featureModule,

                baseKmmModule,
                featureKmmModule
            )
        }
    }

}