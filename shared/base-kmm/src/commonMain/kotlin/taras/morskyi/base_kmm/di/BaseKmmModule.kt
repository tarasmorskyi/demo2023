package taras.morskyi.base_kmm.di

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import taras.morskyi.base_kmm.common.DemoHttpClient
import taras.morskyi.base_kmm.common.DemoHttpClientConfig

val baseKmmModule = module {
    single {
        DemoHttpClientConfig(
            "https://whiskyhunter.net/api/"
        )
    }

    single {
        DemoHttpClient(
            get(),
            HttpClient {
                applyDefaultHttpClientConfig()
            }
        )
    }
}

private val applyDefaultHttpClientConfig: HttpClientConfig<*>.() -> Unit
    get() {
        return {
            install(ContentNegotiation) {
                json(Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    allowSpecialFloatingPointValues = true
                    useArrayPolymorphism = true
                    coerceInputValues = true
                    useAlternativeNames = false
                })
            }
        }
    }