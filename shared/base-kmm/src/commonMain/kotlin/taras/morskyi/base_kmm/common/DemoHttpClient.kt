package taras.morskyi.base_kmm.common

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import taras.morskyi.base_kmm.data.api.errors.HttpError

class KtorRequestParams(
    val url: String,
    val emptyBaseUrl: Boolean = false,
    val httpMethod: HttpMethod,
    val headers: Map<String, String> = emptyMap(),
    val body: Any? = null,
    val queries: Map<String, Any?> = emptyMap(),
    val formEncodedFields: Map<String, String?> = emptyMap(),
)

data class DemoHttpClientConfig(
    val baseUrl: String,
)

class DemoHttpClient(
    val config: DemoHttpClientConfig,
    val client: HttpClient,
) {

    suspend inline fun <reified T> request(params: KtorRequestParams): T {
        return performRequest<T>(params).data
    }

    suspend inline fun <reified T> requestWithHeaders(params: KtorRequestParams): ResponseContainer<T> {
        return performRequest(params)
    }

    suspend inline fun <reified T> performRequest(
        params: KtorRequestParams
    ): ResponseContainer<T> {
        val body: suspend () -> ResponseContainer<T> = {

            val result = kotlin.runCatching {

                val baseUrl = config.baseUrl.takeIf { !params.emptyBaseUrl }.orEmpty() + params.url

                val result: HttpResponse = client.request(baseUrl) {
                    method = params.httpMethod
                    headers { params.headers.forEach { append(it.key, it.value) } }
                    if (params.formEncodedFields.isEmpty()) {
                        contentType(ContentType.Application.Json)
                    } else {
                        contentType(ContentType.Application.FormUrlEncoded)
                    }
                    params.queries.forEach {
                        parameter(it.key, it.value)
                    }
                    when {
                        params.body != null -> setBody(params.body)
                        params.formEncodedFields.isNotEmpty() -> {
                            setBody(
                                FormDataContent(Parameters.build {
                                    params.formEncodedFields.forEach {
                                        if (it.value != null)
                                            append(it.key, it.value!!)
                                    }
                                })
                            )
                        }
                    }
                }

                result
            }.getOrElse { exception ->
                if (exception is ResponseException) {
                    throw HttpError(exception.response.status.value, params.url)
                } else {
                    throw exception
                }
            }

            ResponseContainer(result.headers, result.body())
        }
        return body()
    }
}

data class ResponseContainer<T>(
    val headers: Headers,
    val data: T
)

enum class DefaultHeaders(val header: Pair<String, String>) {
    JsonContentType(Pair("Content-Type", "application/json")),
    ModifiedSince(Pair("If-Modified-Since", ""))
}