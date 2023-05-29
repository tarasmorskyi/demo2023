package taras.morskyi.distillers_kmm.data.models.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import taras.morskyi.base_kmm.extensions.orZero
import taras.morskyi.distillers_kmm.data.models.domain.Distiller

@Serializable
data class DistillerResponse(
    val name:String? = null,
    val slug: String? = null,
    val country: String? = null,
    @SerialName("whiskybase_whiskies")val whiskybaseWhiskies: String? = null,
    @SerialName("whiskybase_votes")val whiskybaseVotes: String? = null,
    @SerialName("whiskybase_rating")val whiskybaseRating: String? = null,
) {
    fun toDomain() = Distiller(
        name = name.orEmpty(),
        slug = slug.orEmpty(),
        country = country.orEmpty(),
        whiskies = whiskybaseWhiskies?.toIntOrNull().orZero(),
        votes = whiskybaseVotes?.toIntOrNull().orZero(),
        rating = whiskybaseRating?.toDoubleOrNull().orZero(),
    )
}