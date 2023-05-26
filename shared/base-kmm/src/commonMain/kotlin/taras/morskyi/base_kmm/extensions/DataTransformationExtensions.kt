package taras.morskyi.base_kmm.extensions

@Throws(NullPointerException::class)
fun <T> T?.orThrowNPE(): T {
    return this ?: throw NullPointerException()
}

fun Boolean?.orFalse() = this ?: false
fun Boolean?.orTrue() = this ?: true

fun Double?.orZero() = this ?: 0.0
fun Float?.orZero() = this ?: 0.0f
fun Int?.orZero() = this ?: 0
fun Long?.orZero() = this ?: 0L

fun String?.takeNotEmpty() = this?.takeIf { it.isNotEmpty() }

fun String?.isNotNullOrBlank() = this.isNullOrBlank().not()

fun <T> T.asList() = listOf<T>(this)