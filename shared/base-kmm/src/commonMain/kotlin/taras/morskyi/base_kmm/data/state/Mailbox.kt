package taras.morskyi.base_kmm.data.state

class Mailbox<T>(initValue: T?) {
    var value: T? = initValue
        get() {
            val result = field
            field = null
            return result
        }
        private set
}