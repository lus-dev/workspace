package lus.areapass.network

import lus.areapass.entities.Pass
import lus.areapass.entities.PassForm
import lus.areapass.entities.credentials.ICredentials
import lus.areapass.entities.discount.IDiscount
import lus.areapass.entities.discount.ZeroDiscount
import lus.areapass.entities.network.IAccount
import lus.areapass.entities.network.RetrofittableAccount
import lus.areapass.entities.person.Contact
import lus.areapass.entities.person.IContact
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RetrofittableApiService @Inject constructor(
    private val api: Api
) : ApiService, PassApiService {

    private suspend inline fun <T> execute(apiRequest: () -> T): Response<T> {
        return try {
            Success(apiRequest())
        } catch (exc: Throwable) {
            Error("Something went wrong. Try again later")
        }
    }

    override suspend fun signIn(credentials: ICredentials): Response<IAccount> {
        return execute { RetrofittableAccount(Contact("Sasha", "Lysenko", "lus", ""), credentials, ZeroDiscount()) }
    }

    override suspend fun createAccount(contact: IContact, credentials: ICredentials, discount: IDiscount): Response<IAccount> {
        return execute { RetrofittableAccount(contact, credentials, discount) }
    }

    override suspend fun resetPassword(identifier: String): Response<Unit> {
        return execute { Unit }
    }

    override suspend fun fetchEndingPasses(credentials: ICredentials): Response<List<Pass>> {
        return execute {
            listOf(
                createStubPass("Icehall", 14),
                createStubPass("Forma", 0)
//                createStubPass("Icehall", 2),
//                createStubPass("Forma", 6),
//                createStubPass("Icehall", 12),
//                createStubPass("Forma", null)
            )
        }
    }

    override suspend fun updateAccount(data: IAccount): Response<IAccount> {
        return execute { data }
    }

    override suspend fun changePassword(data: ICredentials): Response<Unit> {
        return execute { Unit }
    }

    private fun createStubPass(name: String, totalVisits: Int): Pass {
        val data = Pass(-1, PassForm(name, 1569924000000, "", totalVisits))
//        data.creationDate = 1567332000000
//        data.userVisits = 0
        return data
    }

    override suspend fun create(pass: PassForm): Response<Pass> {
        return execute { Pass(-1, pass) }
    }

}