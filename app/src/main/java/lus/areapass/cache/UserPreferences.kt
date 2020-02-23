package lus.areapass.cache

import com.cocosw.favor.AllFavor
import lus.areapass.entities.User


class UserPreferences constructor(private val storage: UserFieldStorage) {

    fun save(data: User) {
        storage.id = data.id
        storage.username = data.username
        storage.firstName = data.firstName
        storage.lastName = data.lastName
        storage.email = data.email
    }

    fun load() : User {
        val data = User()
        data.id = storage.id
        data.username = storage.username
        data.firstName = storage.firstName
        data.lastName = storage.lastName
        data.email = storage.email
        return data
    }

    fun markNonAuthorized() {
        storage.id = null
    }

}

@AllFavor
interface UserFieldStorage {

    var id: Long?
    var username: String?
    var firstName: String?
    var lastName: String?
    var email: String?
    var hasPromoCode: Boolean

}