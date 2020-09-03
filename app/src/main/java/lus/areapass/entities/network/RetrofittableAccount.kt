package lus.areapass.entities.network

import lus.areapass.entities.person.IContact
import lus.areapass.entities.credentials.ICredentials
import lus.areapass.entities.discount.IDiscount


class RetrofittableAccount (
    override val contact: IContact,
    override val credentials: ICredentials,
    override val discount: IDiscount
): IAccount