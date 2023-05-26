package taras.morskyi.base_kmm.data.api.errors

data class HttpError(
    val statusCode: Int,
    val url: String
): Throwable()