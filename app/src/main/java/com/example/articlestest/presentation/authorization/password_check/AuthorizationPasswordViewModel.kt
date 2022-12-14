package com.example.articlestest.presentation.authorization.password_check

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.articlestest.domain.repositories.AuthorisationRepository
import com.example.articlestest.presentation.base.BaseViewModel
import com.example.articlestest.presentation.base.BaseViewState
import com.example.articlestest.presentation.navigation.NavDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthorizationPasswordViewModel @Inject constructor(
    private val repository: AuthorisationRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<BaseViewState<AuthorizationPasswordViewState>, AuthorizationPasswordEvent>() {


    private fun signIn(phone: String, password: String) {
        setState(BaseViewState.Loading)
        viewModelScope.launch(coroutineExceptionHandler) {
            val passwordCheck = repository.signIn(phone = phone, password = password)

            if (passwordCheck) {
                val isNotEmptyProfile = repository.isNotEmptyProfile()

                if (isNotEmptyProfile) {
                    onNavigationEvent(NavDestination.AppMain)
                } else onNavigationEvent(NavDestination.RegistrationUserData)

            } else setState(BaseViewState.Error(Throwable("Invalid password")))

        }
    }

    override fun onTriggerEvent(eventType: AuthorizationPasswordEvent) {
        when (eventType) {
            is AuthorizationPasswordEvent.SignIn -> signIn(eventType.phone, eventType.password)
        }
    }

    override fun onNavigationEvent(eventType: NavDestination) {
        navigationState.value = eventType
    }
}
