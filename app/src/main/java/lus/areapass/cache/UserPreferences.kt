package lus.areapass.cache

import android.content.Context
import android.content.SharedPreferences
import lus.areapass.entities.credentials.IdCredentials
import lus.areapass.entities.network.IAccount
import lus.areapass.entities.person.Contact
import lus.areapass.entities.person.IContact
import lus.areapass.entities.network.User


class UserPreferences constructor(appContext: Context) : ICachedUser {

    private val keyFieldID = "id"
    private val keyFieldFirstName = "firstName"
    private val keyFieldLastName = "lastName"
    private val keyFieldUsername = "username"
    private val keyFieldEmail = "email"

    private val preferences: SharedPreferences =
        appContext.getSharedPreferences("lus.areapass.preferences.user", Context.MODE_PRIVATE)

    fun save(user: IAccount) {
        save(user.contact)
        preferences.edit()
            .putString(keyFieldID, user.credentials.identifier())
            .apply()
    }

    fun save(contact: IContact) {
        preferences.edit()
            .putString(keyFieldFirstName, contact.firstName)
            .putString(keyFieldLastName, contact.lastName)
            .putString(keyFieldUsername, contact.username)
            .putString(keyFieldEmail, contact.email)
            .apply()
    }

    fun user(): IAccount {
        with(preferences) {
            return User(
                Contact(
                    getString(keyFieldFirstName, ""),
                    getString(keyFieldLastName, ""),
                    getString(keyFieldUsername, ""),
                    getString(keyFieldEmail, "")),
                IdCredentials(getString(keyFieldID, ""))
            )
        }
    }

    fun unauthorize() = preferences.edit().clear().apply()

    fun authorized() = preferences.contains(keyFieldID)

    override fun contact(): IContact {
        with(preferences) {
            return Contact(
                getString(keyFieldFirstName, ""),
                getString(keyFieldLastName, ""),
                getString(keyFieldUsername, ""),
                getString(keyFieldEmail, "")
            )
        }
    }

}