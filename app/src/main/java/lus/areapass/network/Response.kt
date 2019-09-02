package lus.areapass.network


sealed class Response<D>
data class Success<D>(val data: D) : Response<D>()
data class Error<D>(val message: String) : Response<D>()