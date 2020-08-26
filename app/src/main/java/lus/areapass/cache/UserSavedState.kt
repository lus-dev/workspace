package lus.areapass.cache

import androidx.lifecycle.SavedStateHandle
import lus.areapass.entities.person.Contact
import lus.areapass.entities.person.IContact


class UserSavedState constructor(private val state: SavedStateHandle): ICachedUser {

    private val keyFieldFirstName = "firstName"
    private val keyFieldLastName = "lastName"
    private val keyFieldUsername = "username"
    private val keyFieldEmail = "email"

    fun save(user: IContact) {
        state.set(keyFieldFirstName, user.firstName)
        state.set(keyFieldLastName, user.lastName)
        state.set(keyFieldUsername, user.username)
        state.set(keyFieldEmail, user.email)
    }

     override fun contact(): IContact = Contact(
         state.get(keyFieldFirstName) ?: "",
         state.get(keyFieldLastName) ?: "",
         state.get(keyFieldUsername) ?: "",
         state.get(keyFieldEmail) ?: ""
     )

    fun contains() = state.keys().isNotEmpty()

}