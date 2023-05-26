package taras.morskyi.base_kmm.realm

import io.realm.kotlin.TypedRealm
import io.realm.kotlin.query.RealmQuery
import io.realm.kotlin.types.BaseRealmObject
import kotlin.reflect.KClass

class RealmFilter<T : BaseRealmObject> internal constructor(
    private val realm: TypedRealm,
    private val clazz: KClass<T>
) {

    val queries = ArrayList<String>()

    fun group(init: RealmFilter<T>.() -> Unit) {
        queries.add("(")
        this.init()
        queries.add(")")
    }

    fun equalTo(key: String, value: Any) {
        queries.add(
            "$key == ${
                when (value) {
                    is String -> "\"$value\""
                    else -> value.toString()
                }
            }"
        )
    }

    fun notEqualTo(key: String, value: Any) {
        queries.add(
            "$key != ${
                when (value) {
                    is String -> "\"$value\""
                    else -> value.toString()
                }
            }"
        )
    }

    fun inList(key: String, value: List<Any>) {
        if (value.isEmpty()) return
        group {
            equalTo(key, value.first())
            value.toMutableList().apply {
                removeFirstOrNull()
            }.forEach {
                or()
                equalTo(key, it)
            }
        }
    }

    fun contains(key: String, value: String, caseSensitivity: Case?) {
        queries.add("$key contains${caseSensitivity?.value.orEmpty()} \"$value\"")
    }

    fun greaterThan(key: String, value: Any) {
        queries.add("$key > $value")
    }

    fun greaterThanOrEquals(key: String, value: Any) {
        queries.add("$key >= $value")
    }

    fun lessThan(key: String, value: Any) {
        queries.add("$key < $value")
    }

    fun lessThanOrEquals(key: String, value: Any) {
        queries.add("$key <= $value")
    }

    fun sort(key: String, sort: Sort) {
        queries.add("SORT($key ${sort.value})")
    }

    fun or() {
        queries.add("or")
    }

    fun and() {
        queries.add("and")
    }

    fun limit(limit: Int) {
        queries.add("LIMIT($limit)")
    }

    internal fun findAll(): RealmQuery<T> {
        val query = buildQuery()
        if (query.isBlank()) {
            return realm.query(clazz)
        }
        return realm.query(clazz, query)
    }

    private fun buildQuery(): String {
        return queries.joinToString(" ")
    }
}

fun <T : BaseRealmObject> TypedRealm.where(
    clazz: KClass<T>,
    init: RealmFilter<T>.() -> Unit = {}
): RealmQuery<T> {
    val filter = RealmFilter(this, clazz)
    filter.init()
    return filter.findAll()
}

enum class Case(val value: String) {
    SENSITIVE(""),
    INSENSITIVE("[c]")
}

enum class Sort(val value: String) {
    DESC("DESC"),
    ASC("ASC")
}
