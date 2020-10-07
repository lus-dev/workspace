package lus.areapass.entities.person

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Contact constructor(
    override val firstName: String,
    override val lastName: String,
    override val username: String,
    override val email: String
) : IContact, Parcelable