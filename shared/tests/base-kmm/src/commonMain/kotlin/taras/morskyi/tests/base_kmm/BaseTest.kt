package taras.morskyi.tests.base_kmm

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.test.KoinTest
import kotlin.test.BeforeTest
import kotlin.test.AfterTest

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseTest: KoinTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @BeforeTest
    open fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @AfterTest
    open fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
        stopKoin()
    }
}