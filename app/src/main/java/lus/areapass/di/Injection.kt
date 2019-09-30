package lus.areapass.di

import android.content.Context
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import lus.areapass.account.viewmodels.AccountDetailsViewModel
import lus.areapass.account.viewmodels.AccountViewModel
import lus.areapass.auth.viewmodels.AuthenticationViewModel
import lus.areapass.auth.viewmodels.CreateAccountViewModel
import lus.areapass.auth.viewmodels.ResetPasswordViewModel
import lus.areapass.dashboard.viewmodels.DashboardViewModel
import lus.areapass.auth.viewmodels.SignInViewModel
import lus.areapass.di.modules.ApiServiceModule
import lus.areapass.di.modules.PreferencesModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AssistedInjectModule::class, ApiServiceModule::class, PreferencesModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appContext: Context): ApplicationComponent
    }

    val authenticationViewModel: AuthenticationViewModel
    val signInViewModel: SignInViewModel
    val createAccountViewModel: CreateAccountViewModel
    val resetPasswordViewModel: ResetPasswordViewModel
    val dashboardViewModel: DashboardViewModel // DashboardViewModel.Factory
    val accountViewModel: AccountViewModel
    val accountDetailsViewModel: AccountDetailsViewModel

}

@AssistedModule
@Module(includes = [AssistedInject_AssistedInjectModule::class])
interface AssistedInjectModule