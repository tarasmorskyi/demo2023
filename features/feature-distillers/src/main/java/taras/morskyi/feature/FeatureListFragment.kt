package taras.morskyi.feature

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import taras.morskyi.base.BaseFragment
import taras.morskyi.base.host.NavigationHost
import taras.morskyi.base.viewbinding.viewBinding
import taras.morskyi.feature.databinding.FragmentFeatureBinding
import taras.morskyi.feature.databinding.FragmentFeatureListBinding
import taras.morskyi.feature_kmm.viewmodels.FeatureViewModel

class FeatureListFragment: BaseFragment<NavigationHost>() {

    override val viewModel: FeatureViewModel by viewModel()

    override val layoutRes: Int = R.layout.fragment_feature_list

    val binding by viewBinding(FragmentFeatureListBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setOnClickListener {
            host?.navigateTo(FeatureFragment(), true)
        }
    }

}