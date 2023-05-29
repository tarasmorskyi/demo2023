package taras.morskyi.distillers_kmm.data.models.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import taras.morskyi.distillers_kmm.data.models.entities.DistilleryEntity
import taras.morskyi.distillers_kmm.data.models.viewdata.DistilleryViewData

data class Distiller(
    val name: String,
    val slug: String,
    val country: String,
    val whiskies: Int,
    val votes: Int,
    val rating: Double,
) {
    fun toEntity() = DistilleryEntity().apply {
        name = this@Distiller.name
        slug = this@Distiller.slug
        country = this@Distiller.country
        whiskies = this@Distiller.whiskies
        votes = this@Distiller.votes
        rating = this@Distiller.rating
    }

    fun toViewData() = DistilleryViewData(
        name, slug, country, whiskies, votes, rating
    )
}