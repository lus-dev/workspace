package lus.areapass.di.modules

import dagger.Binds
import dagger.Module
import lus.areapass.network.ApiService
import lus.areapass.network.RetrofittableApiService


@Module(includes = [NetworkModule::class])
interface ApiServiceModule {

    @Binds
    fun provideApiService(apiService: RetrofittableApiService): ApiService

}
