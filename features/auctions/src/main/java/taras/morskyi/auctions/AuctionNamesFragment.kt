package taras.morskyi.auctions

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import taras.morskyi.auctions.adapters.AuctionNameAdapter
import taras.morskyi.auctions.databinding.FragmentAuctionsListBinding
import taras.morskyi.auctions_kmm.data.models.viewdata.AuctionNameViewData
import taras.morskyi.auctions_kmm.viewmodels.AuctionsViewModel
import taras.morskyi.base.BaseFragment
import taras.morskyi.base.host.NavigationHost
import taras.morskyi.base.viewbinding.viewBinding

class AuctionNamesFragment: BaseFragment<NavigationHost>() {

    override val viewModel: AuctionsViewModel by activityViewModel()

    override val layoutRes: Int = R.layout.fragment_auctions_list

    val binding by viewBinding(FragmentAuctionsListBinding::bind)

    lateinit var adapter: AuctionNameAdapter

    override fun observeData() {
        super.observeData()
        viewModel.viewState.observe { viewState ->
            adapter.setItems(viewState.auctions)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationIcon(R.drawable.ic_back_light)
        binding.toolbar.setNavigationOnClickListener {
            host?.goBack()
        }
        adapter = AuctionNameAdapter(context, ::onAuctionClick)
        binding.list.adapter = adapter
    }

    private fun onAuctionClick(auction: AuctionNameViewData) {
        host?.navigateTo(AuctionsFragment.getInstance(auction.slug), true)
    }

}