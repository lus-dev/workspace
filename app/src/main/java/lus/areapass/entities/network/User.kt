package lus.areapass.entities.network

import lus.areapass.entities.credentials.ICredentials
import lus.areapass.entities.discount.ZeroDiscount
import lus.areapass.entities.person.IContact


class User(
    override val contact: IContact,
    override val credentials: ICredentials
) : IAccount {

    override val discount = ZeroDiscount()

}