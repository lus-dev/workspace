package lus.areapass.cache

import lus.areapass.entities.person.IContact

interface ICachedUser {

    fun contact(): IContact

}