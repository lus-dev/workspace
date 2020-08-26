package lus.areapass.entities.credentials

import lus.areapass.entities.credentials.ICredentials

class UsernameCredentials constructor(
    private val username: String,
    private val password: String
): ICredentials {

    override fun identifier() = username

    override fun password() = password

}