package taras.morskyi.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.NO_ID
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import taras.morskyi.base.host.Host
import taras.morskyi.base_kmm.common.BaseViewModel
import taras.morskyi.base_kmm.common.KotlinLiveData
import taras.morskyi.base_kmm.data.api.errors.HttpError

abstract class BaseFragment<T : Host> : Fragment() {

    var host: T? = null

    protected abstract val viewModel: BaseViewModel

    protected open val layoutRes: Int = NO_ID

    @CallSuper
    protected open fun observeData() {
        viewModel.run {
            viewState.observe { state ->
                handleProgress(state.loading)
                onError(state.error.value)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context as? T != null) {
            host = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.takeUnless { layoutRes == NO_ID }?.inflate(layoutRes, container, false)

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeData()
    }

    private fun handleProgress(showProgress: Boolean) {
        childFragmentManager.run {
            if (showProgress) {
                findFragmentByTag(ProgressDialog.TAG)
                    ?: beginTransaction()
                        .add(ProgressDialog(), ProgressDialog.TAG)
                        .commitNowAllowingStateLoss()
            } else {
                childFragmentManager.findFragmentByTag(ProgressDialog.TAG)?.let {
                    beginTransaction().remove(it).commitAllowingStateLoss()
                }
            }
        }
    }

    private fun onError(throwable: Throwable?) {
        when (throwable) {
            is HttpError -> Toast.makeText(
                activity,
                "${throwable.statusCode} for ${throwable.url}",
                Toast.LENGTH_LONG
            ).show()

            else -> if (throwable != null) {
                Toast.makeText(activity, throwable.message.orEmpty(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    protected fun navigateTo(fragment: Fragment, container: Int, addToBackStack: Boolean = true) {
        parentFragmentManager.beginTransaction().replace(container, fragment).apply {
            if (addToBackStack) addToBackStack(fragment::class.simpleName)
        }.commit()
    }

    protected inline fun <T> KotlinLiveData<T>.observe(crossinline callback: (T) -> Unit) {
        observe(this@BaseFragment.viewLifecycleOwner, Observer { callback(it) })
    }
}