package taras.morskyi.distillers

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import org.koin.androidx.viewmodel.ext.android.viewModel
import taras.morskyi.base.BaseFragment
import taras.morskyi.base.host.NavigationHost
import taras.morskyi.base.viewbinding.viewBinding
import taras.morskyi.distillers.adapters.DistilleryBidsAdapter
import taras.morskyi.distillers.databinding.FragmentDistillersListBinding
import taras.morskyi.distillers_kmm.viewmodels.DistilleryBidsViewModel

class DistillerBidsFragment : BaseFragment<NavigationHost>() {

    companion object {
        val SLUG = "slug"
        fun getInstance(slug: String) = DistillerBidsFragment().apply {
            arguments = bundleOf(
                SLUG to slug
            )
        }
    }

    override val viewModel: DistilleryBidsViewModel by viewModel()

    override val layoutRes: Int = R.layout.fragment_distillers_list

    val binding by viewBinding(FragmentDistillersListBinding::bind)

    lateinit var adapter: DistilleryBidsAdapter

    override fun observeData() {
        super.observeData()
        viewModel.viewState.observe { viewState ->
            adapter.setItems(viewState.distilleryBids)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(SLUG)?.let { slug ->
            viewModel.getDistilleryBids(slug)
        } ?: host?.goBack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_back_light)
        binding.toolbar.setNavigationOnClickListener {
            host?.goBack()
        }
        adapter = DistilleryBidsAdapter(context)
        binding.list.adapter = adapter
        super.onViewCreated(view, savedInstanceState)
    }

}