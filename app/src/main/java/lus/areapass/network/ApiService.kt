package lus.areapass.network

import lus.areapass.entities.Pass
import lus.areapass.entities.User


interface ApiService {
    suspend fun signIn(identifier: String, password: String): Response<User>
    suspend fun createAccount(data: User): Response<User>
    suspend fun resetPassword(identifier: String): Response<Unit>
    suspend fun fetchEndingPasses(userId: Long): Response<List<Pass>>
}