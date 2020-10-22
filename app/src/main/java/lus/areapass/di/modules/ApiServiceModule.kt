package lus.areapass.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import lus.areapass.network.ApiService
import lus.areapass.network.RetrofittableApiService


@InstallIn(ApplicationComponent::class)
@Module
interface ApiServiceModule {

    @Binds
    fun provideApiService(apiService: RetrofittableApiService): ApiService

}
