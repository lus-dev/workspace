package lus.areapass.cache

import androidx.lifecycle.SavedStateHandle
import lus.areapass.entities.person.Contact
import lus.areapass.entities.person.IContact


class UserSavedState constructor(private val state: SavedStateHandle): ICachedUser {

    private val keyStateContact = "contact"

    fun save(user: IContact) {
        state.set(keyStateContact, user)
    }

    override fun contact(): IContact = state.get<Contact>(keyStateContact)!!

    fun contains() = state.keys().isNotEmpty()

}