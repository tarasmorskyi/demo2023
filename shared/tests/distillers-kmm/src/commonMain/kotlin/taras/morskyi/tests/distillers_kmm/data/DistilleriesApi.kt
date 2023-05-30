package taras.morskyi.tests.distillers_kmm.data

import taras.morskyi.distillers_kmm.data.DistilleriesApi
import taras.morskyi.distillers_kmm.data.models.domain.Distiller
import taras.morskyi.distillers_kmm.data.models.domain.DistilleryBid

class DistilleriesApiMock(
    val getDistillers: suspend () -> List<Distiller> = { emptyList() },
    val getDistilleryData: suspend (String) -> List<DistilleryBid> = { emptyList() }
)

class DistilleriesApiTest(private val mock: DistilleriesApiMock) : DistilleriesApi {
    override suspend fun getDistillers(): List<Distiller> = mock.getDistillers()

    override suspend fun getDistilleryData(slug: String): List<DistilleryBid> =
        mock.getDistilleryData(slug)
}