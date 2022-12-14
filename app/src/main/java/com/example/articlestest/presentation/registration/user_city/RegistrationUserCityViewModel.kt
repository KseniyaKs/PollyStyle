package com.example.articlestest.presentation.registration.user_city

import androidx.lifecycle.viewModelScope
import com.example.articlestest.data.model.City
import com.example.articlestest.domain.repositories.RegistrationRepository
import com.example.articlestest.presentation.base.BaseViewModel
import com.example.articlestest.presentation.base.BaseViewState
import com.example.articlestest.presentation.navigation.NavDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegistrationUserCityViewModel @Inject constructor(
    private val repository: RegistrationRepository,
) : BaseViewModel<BaseViewState<RegistrationUserCityViewState>, RegistrationUserCityEvent>() {

    val citiesState = MutableStateFlow<List<City>>(listOf())

    init {
        onTriggerEvent(eventType = RegistrationUserCityEvent.GetCity)
    }

    private fun getCities() {
        viewModelScope.launch(coroutineExceptionHandler) {
            citiesState.emit(repository.getCities())
        }
    }

    private fun createUserInfo(
        name: String,
        surname: String,
        patronymic: String,
        email: String,
        city: String
    ) {
        setState(BaseViewState.Loading)
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.createUserInfo(name, surname, patronymic, email, city)
            onNavigationEvent(eventType = NavDestination.AppMain)
            delay(1000)
            setState(BaseViewState.Data(RegistrationUserCityViewState.CreateUser))
        }
    }

    override fun onTriggerEvent(eventType: RegistrationUserCityEvent) {
        when (eventType) {
            is RegistrationUserCityEvent.CreateUserInfo -> createUserInfo(
                name = eventType.name,
                surname = eventType.surname,
                patronymic = eventType.patronymic,
                email = eventType.email,
                city = eventType.city
            )
            RegistrationUserCityEvent.GetCity -> getCities()
        }
    }

    override fun onNavigationEvent(eventType: NavDestination) {
        navigationState.value = eventType
    }
}