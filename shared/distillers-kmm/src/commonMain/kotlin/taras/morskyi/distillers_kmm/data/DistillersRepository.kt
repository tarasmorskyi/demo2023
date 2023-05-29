package taras.morskyi.distillers_kmm.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import taras.morskyi.distillers_kmm.data.models.domain.Distiller
import taras.morskyi.distillers_kmm.data.models.domain.DistilleryBid

interface DistillersRepository {

    val distilleriesLoading: StateFlow<Boolean>

    val distilleriesLoadingError: StateFlow<Throwable?>

    fun getDistillers(): Flow<List<Distiller>>

    suspend fun fetchDistillerData(slug: String): List<DistilleryBid>
}

class DistillersRepositoryImpl(
    val database: DistilleryDatabase,
    val api: DistilleriesApi,
    val loadScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
) : DistillersRepository {

    override val distilleriesLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val distilleriesLoadingError: MutableStateFlow<Throwable?> = MutableStateFlow(null)

    init {
        loadDistilleries()
    }

    private fun loadDistilleries() {
        loadScope.launch {
            distilleriesLoading.value = true
            try {
                val distilleries = api.getDistillers()
                database.insertDistilleries(distilleries)
            } catch (error: Throwable) {
                distilleriesLoadingError.value = error
            } finally {
                distilleriesLoading.value = false
            }
        }
    }

    override fun getDistillers(): Flow<List<Distiller>> = database.getDistilleries()

    override suspend fun fetchDistillerData(slug: String): List<DistilleryBid> =
        api.getDistilleryData(slug)

}