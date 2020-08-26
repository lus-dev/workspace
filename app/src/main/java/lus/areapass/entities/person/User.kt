package lus.areapass.entities.person

import lus.areapass.entities.Pass
import java.util.*


data class User(
    val id: Long,
    val contact: IContact
) {

    fun fullName() = "${contact.firstName} ${contact.lastName}"

}