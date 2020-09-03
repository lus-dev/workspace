package lus.areapass.entities.credentials

class IdCredentials constructor(private val id: String): ICredentials {

    override fun identifier() = id

    override fun password() = ""

}