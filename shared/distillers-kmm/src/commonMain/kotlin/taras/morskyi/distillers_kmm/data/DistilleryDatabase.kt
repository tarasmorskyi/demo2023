package taras.morskyi.distillers_kmm.data

import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import taras.morskyi.base_kmm.realm.where
import taras.morskyi.distillers_kmm.data.models.domain.Distiller
import taras.morskyi.distillers_kmm.data.models.entities.DistilleryEntity

interface DistilleryDatabase {
    fun getDistilleries(): Flow<List<Distiller>>
    suspend fun insertDistilleries(distilleries: List<Distiller>)
}

class DistilleryRealm(
    val realm: Realm
) : DistilleryDatabase {
    override fun getDistilleries(): Flow<List<Distiller>> {
        return realm.where(DistilleryEntity::class).asFlow()
            .map { change -> change.list.map { entity -> entity.toDomain() } }
    }

    override suspend fun insertDistilleries(distilleries: List<Distiller>) {
        realm.write {
            distilleries.forEach { distiller ->
                this.copyToRealm(
                    distiller.toEntity(),
                    UpdatePolicy.ALL
                )
            }
        }
    }

}