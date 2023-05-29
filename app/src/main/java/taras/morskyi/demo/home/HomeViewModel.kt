package taras.morskyi.demo.home

import kotlinx.coroutines.flow.emptyFlow
import taras.morskyi.base_kmm.common.BaseViewModel
import taras.morskyi.base_kmm.common.KotlinLiveData
import taras.morskyi.base_kmm.data.state.BaseViewState

class HomeViewModel : BaseViewModel() {
    override val viewState: KotlinLiveData<out BaseViewState> =
        emptyFlow<BaseViewState>().asKotlinLiveData()
}