package taras.morskyi.distillers_kmm.data.models.viewdata

import taras.morskyi.base_kmm.data.state.BaseViewState
import taras.morskyi.base_kmm.data.state.Mailbox

data class DistilleriesViewState(
    val distilleries: List<DistilleryViewData>,
    override val loading: Boolean,
    override val error: Mailbox<Throwable>,
) : BaseViewState(loading, error)

data class DistilleryViewData(
    val name:String,
    val slug: String,
    val country: String,
    val whiskies: Int,
    val votes: Int,
    val rating: Double,
)