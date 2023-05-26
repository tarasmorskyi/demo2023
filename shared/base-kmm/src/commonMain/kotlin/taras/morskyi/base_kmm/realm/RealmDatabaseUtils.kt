package taras.morskyi.base_kmm.realm

import io.github.aakira.napier.Napier
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.migration.RealmMigration
import io.realm.kotlin.types.BaseRealmObject
import kotlin.reflect.KClass

fun createRealm(config: RealmDatabaseConfig): Realm {
    val realmConfiguration = RealmConfiguration
        .Builder(config.models)
        .name(config.fileName)
        .schemaVersion(config.schemaVersion)
        .compactOnLaunch { totalBytes, usedBytes -> totalBytes > (usedBytes * 2) }
        .apply {
            if (config.migration != null) {
                migration(config.migration)
            } else {
                deleteRealmIfMigrationNeeded()
            }
        }
        .apply { config.encryptionKey?.let { encryptionKey(it) } }
        .build()

    return try {
        Realm.open(realmConfiguration)
    } catch (e: Exception) {
        Napier.e { "Realm open failed! $e - Deleting realm and retrying..." }
        Realm.deleteRealm(realmConfiguration)
        Realm.open(realmConfiguration)
    }
}

data class RealmDatabaseConfig(
    val fileName: String,
    val schemaVersion: Long,
    val models: Set<KClass<out BaseRealmObject>>,
    val encryptionKey: ByteArray? = null,
    val migration: RealmMigration? = null,
) {

    // Overridden equals() & hashCode() because an array is used in the data class
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as RealmDatabaseConfig

        if (fileName != other.fileName) return false
        if (schemaVersion != other.schemaVersion) return false
        if (models != other.models) return false
        if (migration != other.migration) return false
        if (encryptionKey != null) {
            if (other.encryptionKey == null) return false
            if (!encryptionKey.contentEquals(other.encryptionKey)) return false
        } else if (other.encryptionKey != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fileName.hashCode()
        result = 31 * result + schemaVersion.hashCode()
        result = 31 * result + models.hashCode()
        result = 31 * result + (migration?.hashCode() ?: 0)
        result = 31 * result + (encryptionKey?.contentHashCode() ?: 0)
        return result
    }
}