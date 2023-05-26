package taras.morskyi.base_kmm.common

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import taras.morskyi.base_kmm.data.state.BaseViewState
import taras.morskyi.base_kmm.data.state.Mailbox

abstract class BaseViewModel() : KotlinViewModel() {
    //    abstract val state: Flow<VS>
    protected val isLoading = MutableStateFlow(false)

    protected val errors = MutableStateFlow<Mailbox<Throwable>>(Mailbox(null))

    abstract val viewState: KotlinLiveData<out BaseViewState>

    protected fun launch(
        silent: Boolean = false,
        errorCall: (Throwable) -> Throwable? = { it },
        onComplete: () -> Unit = {},
        remoteCall: suspend () -> Unit
    ) =
        kmmViewModelScope.launch(Dispatchers.Default) {
            runCatching {
                remoteCall()
                onComplete()
            }.onFailure {
                val error = errorCall(it) ?: return@onFailure
                if (!silent && error !is CancellationException) errors.value = Mailbox(error)
            }
        }

    protected fun launchWithProgress(
        silent: Boolean = false,
        errorCall: suspend (Throwable) -> Throwable? = { it },
        onComplete: suspend () -> Unit = {},
        remoteCall: suspend () -> Unit
    ) =
        kmmViewModelScope.launch(Dispatchers.Default) {
            isLoading.value = true
            runCatching {
                remoteCall()
                onComplete()
            }.onFailure {
                val error = errorCall(it) ?: return@onFailure
                if (!silent && error !is CancellationException) errors.value = Mailbox(error)
            }
            isLoading.value = false
        }
}