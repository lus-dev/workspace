package lus.areapass.cache

import com.cocosw.favor.AllFavor
import lus.areapass.entities.User


class UserPreferences constructor(private val storage: UserFieldStorage) {

    fun save(data: User) {
        storage.id = data.id
    }

    fun load() : User {
        val data = User()
        data.id = storage.id
        return data
    }

}

@AllFavor
interface UserFieldStorage {

    var id: Int?
    var firstName: String?
    var lastName: String?
    var hasPromoCode: Boolean

}