package taras.morskyi.distillers

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import taras.morskyi.base.BaseFragment
import taras.morskyi.base.host.NavigationHost
import taras.morskyi.base.viewbinding.viewBinding
import taras.morskyi.distillers.adapters.DistilleriesAdapter
import taras.morskyi.distillers.databinding.FragmentDistillersListBinding
import taras.morskyi.distillers_kmm.data.models.viewdata.DistilleryViewData
import taras.morskyi.distillers_kmm.viewmodels.DistilleriesViewModel

class DistillersFragment : BaseFragment<NavigationHost>() {

    override val viewModel: DistilleriesViewModel by viewModel()

    override val layoutRes: Int = R.layout.fragment_distillers_list

    lateinit var adapter: DistilleriesAdapter
    val binding by viewBinding(FragmentDistillersListBinding::bind)

    override fun observeData() {
        super.observeData()
        viewModel.viewState.observe { viewState ->
            adapter.setItems(viewState.distilleries)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_back_light)
        binding.toolbar.setNavigationOnClickListener {
            host?.goBack()
        }
        adapter = DistilleriesAdapter(context, ::onDistilleryClick)
        binding.list.adapter = adapter
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onDistilleryClick(distilleryViewData: DistilleryViewData) {
        host?.navigateTo(DistillerBidsFragment.getInstance(distilleryViewData.slug), true)
    }
}