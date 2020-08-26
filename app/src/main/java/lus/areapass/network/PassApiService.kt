package lus.areapass.network

import lus.areapass.entities.Pass
import lus.areapass.entities.PassForm


interface PassApiService {

    suspend fun create(pass: PassForm): Response<Pass>

}