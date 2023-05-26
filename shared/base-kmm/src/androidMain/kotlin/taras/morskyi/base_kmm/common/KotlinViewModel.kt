package taras.morskyi.base_kmm.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent

actual open class KotlinViewModel actual constructor() : ViewModel(), KoinComponent {
    actual val kmmViewModelScope: CoroutineScope
        get() = viewModelScope

    override fun onCleared() {
        super.onCleared()
        onClearedKmm()
    }

    protected actual open fun onClearedKmm() {
    }

    protected actual fun <T> Flow<T>.asKotlinLiveData(): KotlinLiveData<T> {
        return KotlinLiveData(this.asLiveData())
    }

}