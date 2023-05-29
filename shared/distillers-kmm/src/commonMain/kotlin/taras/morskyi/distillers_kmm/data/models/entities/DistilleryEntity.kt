package taras.morskyi.distillers_kmm.data.models.entities

import io.realm.kotlin.types.RealmObject
import kotlinx.serialization.SerialName
import taras.morskyi.distillers_kmm.data.models.domain.Distiller

class DistilleryEntity: RealmObject {
    var name:String = ""
    var slug: String = ""
    var country: String = ""
    var whiskies: Int = 0
    var votes: Int = 0
    var rating: Double = 0.0

    fun toDomain() = Distiller(
        name, slug, country, whiskies, votes, rating
    )
}