package taras.morskyi.base.host

import androidx.fragment.app.Fragment

interface Host

interface NavigationHost : Host {
    fun navigateTo(fragment: Fragment, addToBackstack: Boolean): String
    fun goBack()
}