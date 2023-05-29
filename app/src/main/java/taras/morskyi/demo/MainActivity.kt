package taras.morskyi.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import taras.morskyi.app.R
import taras.morskyi.base.host.NavigationHost
import taras.morskyi.demo.home.HomeFragment
import taras.morskyi.distillers.DistillersFragment


class MainActivity : AppCompatActivity(), NavigationHost {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateTo(HomeFragment(), false)
    }

    override fun navigateTo(fragment: Fragment, addToBackStack: Boolean): String {
        return navigateTo(fragment, addToBackStack, R.id.fragment_container)
    }

    override fun goBack() {
        onBackPressedDispatcher.onBackPressed()
    }

    private fun navigateTo(
        fragment: Fragment,
        addToBackStack: Boolean,
        containerId: Int
    ): String {
        val fragmentTag: String = fragment.javaClass.simpleName
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(containerId, fragment, fragmentTag)
        if (addToBackStack) {
            transaction.addToBackStack(fragmentTag)
        }
        transaction.commit()
        return fragmentTag
    }
}
