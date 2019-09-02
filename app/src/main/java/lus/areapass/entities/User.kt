package lus.areapass.entities

import android.util.Patterns


class User constructor() {

    var id: Int? = null
    var username: String? = null
    var email: String? = null
    var password: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var promoCode: String? = null

    constructor(identifier: String?, password: String?) : this() {
        this.password = password
        determineIdentifier(identifier)
    }

    private fun determineIdentifier(identifier: String?) {
        if (identifier != null) {
            if (Patterns.EMAIL_ADDRESS.matcher(identifier).matches()) {
                email = identifier
            } else {
                username = identifier
            }
        }
    }

}