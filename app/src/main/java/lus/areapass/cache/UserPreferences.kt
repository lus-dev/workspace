package lus.areapass.cache

import com.cocosw.favor.AllFavor
import lus.areapass.entities.User


class UserPreferences constructor(private val storage: UserFieldStorage) {

    fun save(data: User) {
        storage.id = data.id
        storage.firstName = data.firstName
        storage.lastName = data.lastName
    }

    fun load() : User {
        val data = User()
        data.id = storage.id
        data.firstName = storage.firstName
        data.lastName = storage.lastName
        return data
    }

    fun markNonAuthorized() {
        storage.id = null
    }

}

@AllFavor
interface UserFieldStorage {

    var id: Long?
    var firstName: String?
    var lastName: String?
    var hasPromoCode: Boolean

}