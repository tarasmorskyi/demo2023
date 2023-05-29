package taras.morskyi.tests.distillers_kmm.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import taras.morskyi.distillers_kmm.data.DistilleryDatabase
import taras.morskyi.distillers_kmm.data.models.domain.Distiller

class DistilleryDatabaseMock(
    val getDistilleries: () -> Flow<List<Distiller>> = { emptyFlow() },
    val insertDistilleries: (List<Distiller>) -> Unit = {}
)

class DistilleryDatabaseTest(private val mock: DistilleryDatabaseMock) : DistilleryDatabase {
    override fun getDistilleries(): Flow<List<Distiller>> = mock.getDistilleries()

    override suspend fun insertDistilleries(distilleries: List<Distiller>) =
        mock.insertDistilleries(distilleries)
}