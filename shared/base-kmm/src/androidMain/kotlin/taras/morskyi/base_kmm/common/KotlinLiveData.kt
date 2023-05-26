package taras.morskyi.base_kmm.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

actual class KotlinLiveData<T> constructor(private val liveData: LiveData<T>) {

    fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        liveData.observe(owner, observer)
    }
}