package taras.morskyi.distillers_kmm.viewmodels

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import taras.morskyi.base_kmm.common.BaseViewModel
import taras.morskyi.base_kmm.common.KotlinLiveData
import taras.morskyi.base_kmm.data.state.Mailbox
import taras.morskyi.base_kmm.extensions.combineStates
import taras.morskyi.distillers_kmm.data.models.viewdata.DistilleriesViewState
import taras.morskyi.distillers_kmm.usecases.GetDistilleriesLoadingErrorUseCase
import taras.morskyi.distillers_kmm.usecases.GetDistilleriesLoadingUseCase
import taras.morskyi.distillers_kmm.usecases.GetDistilleriesUseCase


class DistilleriesViewModel(
    getDistilleriesUseCase: GetDistilleriesUseCase,
    getDistilleriesLoadingUseCase: GetDistilleriesLoadingUseCase,
    getDistilleriesLoadingErrorUseCase: GetDistilleriesLoadingErrorUseCase,
) : BaseViewModel() {

    init {
        launch {
            getDistilleriesLoadingErrorUseCase(Unit).filterNotNull().collectLatest { error ->
                errors.value = Mailbox(error)
            }
        }
    }

    override val viewState: KotlinLiveData<DistilleriesViewState> = combineStates(
        isLoading,
        getDistilleriesLoadingUseCase(Unit),
        errors,
        getDistilleriesUseCase(Unit).stateIn(
            kmmViewModelScope, SharingStarted.WhileSubscribed(),
            emptyList()
        )
    ) { isLoading, distilleriesLoading, errors, distilleries ->
        DistilleriesViewState(
            distilleries,
            isLoading || distilleriesLoading,
            errors
        )
    }.asKotlinLiveData()
}