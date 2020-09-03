package lus.areapass.network

import lus.areapass.entities.*
import lus.areapass.entities.credentials.ICredentials
import lus.areapass.entities.discount.IDiscount
import lus.areapass.entities.network.IAccount
import lus.areapass.entities.person.IContact


interface ApiService {
    suspend fun signIn(credentials: ICredentials): Response<IAccount>
    suspend fun createAccount(contact: IContact, credentials: ICredentials, discount: IDiscount): Response<IAccount>
    suspend fun resetPassword(identifier: String): Response<Unit>
    suspend fun fetchEndingPasses(credentials: ICredentials): Response<List<Pass>>
    suspend fun updateAccount(data: IAccount): Response<IAccount>
    suspend fun changePassword(data: ICredentials): Response<Unit>
}