package lus.areapass.network

import lus.areapass.entities.*
import lus.areapass.entities.credentials.ICredentials
import lus.areapass.entities.discount.IDiscount
import lus.areapass.entities.person.Contact
import lus.areapass.entities.person.IContact
import lus.areapass.entities.person.User
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RetrofittableApiService @Inject constructor(
    private val api: Api
) : ApiService, PassApiService {

    override suspend fun signIn(credentials: ICredentials): Response<User> {
        return try {
            // TODO Clean code
            val data =
                User(-1, Contact("Sasha", "Lysenko", "lus", ""))
            Success(data)
        } catch (exc: Throwable) {
            Error("Something went wrong. Try again later")
        }
    }

    override suspend fun createAccount(contact: IContact, credentials: ICredentials, discount: IDiscount): Response<User> {
        return try {
            // TODO: Use RetrofittableAccount() with the Retrofit API
            Success(User(-1, contact))
        } catch (exc: Throwable) {
            Error("Something went wrong. Try again later")
        }
    }

    override suspend fun resetPassword(identifier: String): Response<Unit> {
        return try {
            Success(Unit)
        } catch (exc: Throwable) {
            Error("Something went wrong. Try again later")
        }
    }

    override suspend fun fetchEndingPasses(credentials: ICredentials): Response<List<Pass>> {
        return try {
            val data = listOf(
                createStubPass("Icehall", 14),
                createStubPass("Forma", null)//,
//                createStubPass("Icehall", 2),
//                createStubPass("Forma", 6),
//                createStubPass("Icehall", 12),
//                createStubPass("Forma", null)
            )
            Success(data)
        } catch (exc: Throwable) {
            Error("Something went wrong. Try again later")
        }
    }

    override suspend fun updateAccount(data: User): Response<User> {
        return try {
            Success(data)
        } catch (exc: Throwable) {
            Error("Something went wrong. Try again later")
        }
    }

    override suspend fun changePassword(data: User): Response<Unit> {
        return try {
            Success(Unit)
        } catch (exc: Throwable) {
            Error("Something went wrong. Try again later")
        }
    }

    private fun createStubPass(name: String, totalVisits: Int?): Pass {
        val data = Pass()
        data.areaName = name
        data.creationDate = 1567332000000
        data.expirationDate = 1569924000000
        data.totalVisits = totalVisits
        data.userVisits = 0
        return data
    }

    override suspend fun create(pass: PassForm): Response<Pass> {
        return try {
            Success(Pass(-1, pass))
        } catch (exc: Throwable) {
            Error("Something went wrong. Try again later")
        }
    }
}