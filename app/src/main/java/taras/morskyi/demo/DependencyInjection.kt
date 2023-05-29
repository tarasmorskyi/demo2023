package taras.morskyi.demo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import taras.morskyi.auctions.di.auctionsModule
import taras.morskyi.base.di.baseModule
import taras.morskyi.auctions_kmm.di.auctionsKmmModule
import taras.morskyi.base_kmm.di.baseKmmModule
import taras.morskyi.demo.home.homeModule
import taras.morskyi.distillers.di.distillersModule
import taras.morskyi.distillers_kmm.di.distillersKmmModule

object DependencyInjection {

    fun init(application: Application) {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(application)
            modules(
                baseModule,
                homeModule,
                auctionsModule,
                distillersModule,

                baseKmmModule,
                auctionsKmmModule,
                distillersKmmModule,
            )
        }
    }

}