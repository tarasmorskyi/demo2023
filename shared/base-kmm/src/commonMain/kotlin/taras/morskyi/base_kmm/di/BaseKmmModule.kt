package taras.morskyi.base_kmm.di

import io.ktor.client.HttpClient
import org.koin.dsl.module
import taras.morskyi.base_kmm.common.DemoHttpClient
import taras.morskyi.base_kmm.common.HttpClientConfig

val baseKmmModule = module {
    single {
        HttpClientConfig(
            "baseUrl"
        )
    }

    single {
        DemoHttpClient(
            get(),
            HttpClient()
        )
    }
}