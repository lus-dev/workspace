package lus.areapass.entities.credentials

import android.util.Patterns
import lus.areapass.entities.credentials.ICredentials
import java.lang.IllegalArgumentException

class EmailCredentials constructor(
    private val email: String,
    private val password: String
): ICredentials {

    override fun identifier(): String {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) return email
        else throw IllegalArgumentException("Invalid email format")
    }

    override fun password() = password

}