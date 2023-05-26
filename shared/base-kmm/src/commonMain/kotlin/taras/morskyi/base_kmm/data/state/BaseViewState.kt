package taras.morskyi.base_kmm.data.state

open class BaseViewState(
    open val loading: Boolean,
    open val error: Mailbox<Throwable>
)