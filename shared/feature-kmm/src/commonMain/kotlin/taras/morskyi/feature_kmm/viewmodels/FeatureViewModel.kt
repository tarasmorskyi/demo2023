package taras.morskyi.feature_kmm.viewmodels

import taras.morskyi.base_kmm.common.BaseViewModel
import taras.morskyi.base_kmm.common.KotlinLiveData
import taras.morskyi.base_kmm.data.state.BaseViewState
import taras.morskyi.base_kmm.data.state.Mailbox
import taras.morskyi.base_kmm.extensions.combineStates

data class FeatureViewState(
    override val loading: Boolean,
    override val error: Mailbox<Throwable>,
) : BaseViewState(loading, error)

class FeatureViewModel : BaseViewModel() {
    override val viewState: KotlinLiveData<FeatureViewState> = combineStates(
        isLoading,
        errors,
    ) { isLoading, errors ->
        FeatureViewState(
            isLoading,
            errors
        )
    }.asKotlinLiveData()
}