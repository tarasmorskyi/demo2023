package taras.morskyi.distillers_kmm.data

import io.ktor.http.HttpMethod
import taras.morskyi.base_kmm.common.DemoHttpClient
import taras.morskyi.base_kmm.common.KtorRequestParams
import taras.morskyi.distillers_kmm.data.models.domain.Distiller
import taras.morskyi.distillers_kmm.data.models.domain.DistilleryBid
import taras.morskyi.distillers_kmm.data.models.network.DistillerResponse
import taras.morskyi.distillers_kmm.data.models.network.DistilleryBidResponse

interface DistilleriesApi {
    suspend fun getDistillers(): List<Distiller>
    suspend fun getDistilleryData(slug: String): List<DistilleryBid>
}

class DistilleriesApiImpl(
    private val client: DemoHttpClient
) : DistilleriesApi {
    override suspend fun getDistillers(): List<Distiller> = client.request<List<DistillerResponse>>(
        KtorRequestParams(
            httpMethod = HttpMethod.Get,
            url = "distilleries_info/?format=json"
        )
    ).map { networkObject ->
        networkObject.toDomain()
    }

    override suspend fun getDistilleryData(slug: String): List<DistilleryBid> =
        client.request<List<DistilleryBidResponse>>(
            KtorRequestParams(
                httpMethod = HttpMethod.Get,
                url = "distillery_data/$slug/?format=json"
            )
        ).map { networkObject ->
            networkObject.toDomain()
        }

}