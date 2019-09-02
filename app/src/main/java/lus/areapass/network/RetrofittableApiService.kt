package lus.areapass.network

import lus.areapass.entities.User
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RetrofittableApiService @Inject constructor(
    private val api: Api
) : ApiService {

    override suspend fun signIn(identifier: String, password: String): Response<User> {
        try {
            // TODO Clean code
            val data = User()
            data.id = -1
            return Success(data)
        } catch (exc: Throwable) {
            return Error("Something went wrong. Try again later")
        }
    }

    override suspend fun createAccount(data: User): Response<User> {
        try {
            return Success(data)
        } catch (exc: Throwable) {
            return Error("Something went wrong. Try again later")
        }
    }

    override suspend fun resetPassword(identifier: String): Response<Unit> {
        try {
            return Success(Unit)
        } catch (exc: Throwable) {
            return Error("Something went wrong. Try again later")
        }
    }
}