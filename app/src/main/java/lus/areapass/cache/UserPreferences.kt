package lus.areapass.cache

import android.content.Context
import android.content.SharedPreferences
import lus.areapass.entities.person.Contact
import lus.areapass.entities.person.User


class UserPreferences constructor(appContext: Context) : ICachedUser {

    private val keyFieldID = "id"
    private val keyFieldFirstName = "firstName"
    private val keyFieldLastName = "lastName"
    private val keyFieldUsername = "username"
    private val keyFieldEmail = "email"

    private val preferences: SharedPreferences =
        appContext.getSharedPreferences("lus.areapass.preferences.user", Context.MODE_PRIVATE)

    fun save(user: User) {
        preferences.edit()
            .putLong(keyFieldID, user.id)
            .putString(keyFieldFirstName, user.contact.firstName)
            .putString(keyFieldLastName, user.contact.lastName)
            .putString(keyFieldUsername, user.contact.username)
            .putString(keyFieldEmail, user.contact.email)
            .apply()
    }

    fun user(): User {
        return User(
            preferences.getLong(keyFieldID, -1),
            Contact(
                preferences.getString(keyFieldFirstName, ""),
                preferences.getString(keyFieldLastName, ""),
                preferences.getString(keyFieldUsername, ""),
                preferences.getString(keyFieldEmail, "")
            )
        )
    }

    fun unauthorize() = preferences.edit().clear().apply()

    fun authorized() = preferences.contains(keyFieldID)

    override fun contact() = Contact(
        preferences.getString(keyFieldFirstName, ""),
        preferences.getString(keyFieldLastName, ""),
        preferences.getString(keyFieldUsername, ""),
        preferences.getString(keyFieldEmail, "")
    )

}