package lus.areapass.entities.network

import lus.areapass.entities.person.IContact
import lus.areapass.entities.credentials.ICredentials
import lus.areapass.entities.discount.IDiscount


class RetrofittableAccount (
    private val contact: IContact,
    private val credentials: ICredentials,
    private val discount: IDiscount
): IAccount {

    override val firstName get() = contact.firstName
    override val lastName get() = contact.lastName
    override val email get() = contact.email
    override val username get() = contact.username
    override val identifier get() = credentials.identifier()
    override val password get() = credentials.password()
    override val promocode get() = discount.code

}