package lus.areapass.entities.credentials

import lus.areapass.entities.credentials.ICredentials

class IdCredentials constructor(private val id: Long): ICredentials {

    override fun identifier() = id.toString()

    override fun password() = ""

}