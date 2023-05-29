package taras.morskyi.demo.home

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import taras.morskyi.app.R
import taras.morskyi.app.databinding.FragmentHomeBinding
import taras.morskyi.auctions.AuctionNamesFragment
import taras.morskyi.base.BaseFragment
import taras.morskyi.base.host.NavigationHost
import taras.morskyi.base.viewbinding.viewBinding
import taras.morskyi.distillers.DistillersFragment

class HomeFragment: BaseFragment<NavigationHost>() {

    override val viewModel: HomeViewModel by viewModel()

    override val layoutRes: Int = R.layout.fragment_home

    val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.auctions.setOnClickListener {
            host?.navigateTo(AuctionNamesFragment(), true)
        }
        binding.distillers.setOnClickListener {
            host?.navigateTo(DistillersFragment(), true)
        }
    }
}