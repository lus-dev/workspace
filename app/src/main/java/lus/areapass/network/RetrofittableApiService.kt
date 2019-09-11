package lus.areapass.network

import lus.areapass.entities.Pass
import lus.areapass.entities.User
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RetrofittableApiService @Inject constructor(
    private val api: Api
) : ApiService {

    override suspend fun signIn(identifier: String, password: String): Response<User> {
        return try {
            // TODO Clean code
            val data = User()
            data.id = -1
            data.firstName = "Sasha"
            data.lastName = "Lysenko"
            Success(data)
        } catch (exc: Throwable) {
            Error("Something went wrong. Try again later")
        }
    }

    override suspend fun createAccount(data: User): Response<User> {
        return try {
            Success(data)
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

    override suspend fun fetchEndingPasses(userId: Long): Response<List<Pass>> {
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

    private fun createStubPass(name: String, totalVisits: Int?): Pass {
        val data = Pass()
        data.areaName = name
        data.creationDate = 1567332000000
        data.expirationDate = 1569924000000
        data.totalVisits = totalVisits
        data.userVisits = 0
        return data
    }

}