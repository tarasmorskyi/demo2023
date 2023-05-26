package taras.morskyi.base_kmm.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent

expect open class KotlinViewModel constructor(): KoinComponent {
    val kmmViewModelScope: CoroutineScope

    protected open fun onClearedKmm()
    protected fun <T> Flow<T>.asKotlinLiveData(): KotlinLiveData<T>
}