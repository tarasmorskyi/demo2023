package taras.morskyi.feature

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import taras.morskyi.base.BaseFragment
import taras.morskyi.base.host.NavigationHost
import taras.morskyi.base.viewbinding.viewBinding
import taras.morskyi.feature.databinding.FragmentFeatureBinding
import taras.morskyi.feature_kmm.viewmodels.FeatureViewModel

class FeatureFragment: BaseFragment<NavigationHost>() {

    override val viewModel: FeatureViewModel by viewModel()

    override val layoutRes: Int = R.layout.fragment_feature

    val binding by viewBinding(FragmentFeatureBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationIcon(R.drawable.ic_back_light)
        binding.toolbar.setNavigationOnClickListener {
            host?.goBack()
        }
    }
}