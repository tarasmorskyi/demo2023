package taras.morskyi.base_kmm.di

import org.koin.core.qualifier.named

object DiConstants {

    val distilleriesRealm = named("distilleriesRealm")

    val distilleriesViewModel = named("distilleriesViewModel")
    val distilleryBidsViewModel = named("distilleryBidsViewModel")
    val auctionsViewModel = named("auctionsViewModel")

}