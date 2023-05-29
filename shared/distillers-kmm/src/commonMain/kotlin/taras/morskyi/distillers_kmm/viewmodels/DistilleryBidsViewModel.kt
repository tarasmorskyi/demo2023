package taras.morskyi.distillers_kmm.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import taras.morskyi.base_kmm.common.BaseViewModel
import taras.morskyi.base_kmm.common.KotlinLiveData
import taras.morskyi.base_kmm.extensions.combineStates
import taras.morskyi.distillers_kmm.data.models.viewdata.DistilleriesViewState
import taras.morskyi.distillers_kmm.data.models.viewdata.DistilleryBidViewData
import taras.morskyi.distillers_kmm.data.models.viewdata.DistilleryBidsViewState
import taras.morskyi.distillers_kmm.usecases.FetchDistilleryBidsUseCase


class DistilleryBidsViewModel(
    val fetchDistilleryBidsUseCase: FetchDistilleryBidsUseCase
) : BaseViewModel() {

    private val distilleryBids = MutableStateFlow(emptyList<DistilleryBidViewData>())

    override val viewState: KotlinLiveData<DistilleryBidsViewState> = combineStates(
        isLoading,
        errors,
        distilleryBids,
    ) { isLoading, errors, distilleryBids ->
        DistilleryBidsViewState(
            distilleryBids,
            isLoading,
            errors
        )
    }.asKotlinLiveData()

    fun getDistilleryBids(slug: String) {
        launchWithProgress {
            distilleryBids.value = fetchDistilleryBidsUseCase(slug)
        }
    }
}