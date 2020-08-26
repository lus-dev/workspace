package lus.areapass.entities.person

class Contact constructor(
    override val firstName: String,
    override val lastName: String,
    override val username: String,
    override val email: String
) : IContact