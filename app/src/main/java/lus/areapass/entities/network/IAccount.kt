package lus.areapass.entities.network

import lus.areapass.entities.credentials.ICredentials
import lus.areapass.entities.discount.IDiscount
import lus.areapass.entities.person.IContact


interface IAccount {

    val contact: IContact
    val credentials: ICredentials
    val discount: IDiscount

}