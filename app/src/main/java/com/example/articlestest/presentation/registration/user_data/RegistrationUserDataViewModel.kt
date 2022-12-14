package com.example.articlestest.presentation.registration.user_data

import com.example.articlestest.presentation.base.BaseViewModel
import com.example.articlestest.presentation.base.BaseViewState
import com.example.articlestest.presentation.navigation.NavDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RegistrationUserDataViewModel @Inject constructor(
) : BaseViewModel<BaseViewState<RegistrationUserDataViewState>, RegistrationUserDataEvent>() {

    private fun createUserCity(
        name: String,
        surname: String,
        patronymic: String,
        email: String
    ) {
        onNavigationEvent(
            NavDestination.RegistrationUserCity(
                name = name,
                surname = surname,
                patronymic = patronymic,
                email = email
            )
        )
    }

    override fun onTriggerEvent(eventType: RegistrationUserDataEvent) {
        when (eventType) {
            is RegistrationUserDataEvent.CreateUserData -> createUserCity(
                name = eventType.name,
                surname = eventType.surname,
                patronymic = eventType.patronymic,
                email = eventType.email
            )
        }
    }

    override fun onNavigationEvent(eventType: NavDestination) {
        navigationState.value = eventType
    }

}