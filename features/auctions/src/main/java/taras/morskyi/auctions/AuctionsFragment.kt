package taras.morskyi.auctions

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import taras.morskyi.auctions.adapters.AuctionAdapter
import taras.morskyi.auctions.databinding.FragmentAuctionsListBinding
import taras.morskyi.auctions_kmm.viewmodels.AuctionsViewModel
import taras.morskyi.base.BaseFragment
import taras.morskyi.base.host.NavigationHost
import taras.morskyi.base.viewbinding.viewBinding

class AuctionsFragment : BaseFragment<NavigationHost>() {

    companion object {
        val SLUG = "slug"
        fun getInstance(slug: String) = AuctionsFragment().apply {
            arguments = bundleOf(
                SLUG to slug
            )
        }
    }

    override val viewModel: AuctionsViewModel by activityViewModel()

    override val layoutRes: Int = R.layout.fragment_auctions_list

    val binding by viewBinding(FragmentAuctionsListBinding::bind)

    lateinit var adapter: AuctionAdapter

    override fun observeData() {
        super.observeData()
        viewModel.viewState.observe { viewState ->
            adapter.setItems(viewState.filteredAuctions)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(SLUG)?.let { slug ->
            viewModel.getAuctions(slug)
        } ?: host?.goBack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationIcon(R.drawable.ic_back_light)
        binding.toolbar.setNavigationOnClickListener {
            host?.goBack()
        }
        adapter = AuctionAdapter(context)
        binding.list.adapter = adapter
    }
}