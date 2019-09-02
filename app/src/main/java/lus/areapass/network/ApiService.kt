package lus.areapass.network

import lus.areapass.entities.User


interface ApiService {
    suspend fun signIn(identifier: String, password: String): Response<User>
    suspend fun createAccount(data: User): Response<User>
    suspend fun resetPassword(identifier: String): Response<Unit>
}