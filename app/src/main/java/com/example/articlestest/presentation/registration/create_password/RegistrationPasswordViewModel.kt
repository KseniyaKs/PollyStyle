package com.example.articlestest.presentation.registration.create_password

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.articlestest.domain.RegistrationRepository
import com.example.articlestest.huinya.base.BaseViewModel
import com.example.articlestest.huinya.base.BaseViewState
import com.example.articlestest.presentation.navigation.NavDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegistrationPasswordViewModel @Inject constructor(
    private val repository: RegistrationRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<BaseViewState<RegistrationPasswordViewState>, RegistrationPasswordEvent>() {

    private fun signUp(phone: String, password: String) {
        setState(BaseViewState.Loading)
        viewModelScope.launch {
            repository.signUp(phone = phone, password = password)
            onNavigationEvent(NavDestination.RegistrationUserData)
        }
    }

    override fun onTriggerEvent(eventType: RegistrationPasswordEvent) {
        when (eventType) {
            is RegistrationPasswordEvent.SignUp -> signUp(
                phone = eventType.phone,
                password = eventType.password
            )
        }
    }

    override fun onNavigationEvent(eventType: NavDestination) {
        navigationState.value = eventType
    }
}