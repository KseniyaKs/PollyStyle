package com.example.articlestest.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.articlestest.data.Api
import com.example.articlestest.data.AuthorisationRepositoryImpl
import com.example.articlestest.data.MainRepositoryImpl
import com.example.articlestest.data.RegistrationRepositoryImpl
import com.example.articlestest.data.mapper.*
import com.example.articlestest.domain.repositories.AuthorisationRepository
import com.example.articlestest.domain.repositories.MainRepository
import com.example.articlestest.domain.repositories.RegistrationRepository
import com.example.articlestest.presentation.base.ResponseMapper
import com.example.articlestest.presentation.base.ResponseMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideAuthorizationRepository(
        api: Api,
        mapper: ResponseMapper,
        mapperFromAuthorizationCheckDtoToModel: MapperFromAuthorizationCheckDtoToModel,
        mapperFromUserPhoneDtoToModel: MapperFromUserPhoneDtoToModel,
        sharedPreferences: DataStore<Preferences>
    ): AuthorisationRepository {
        return AuthorisationRepositoryImpl(
            api,
            mapper,
            mapperFromAuthorizationCheckDtoToModel,
            mapperFromUserPhoneDtoToModel,
            sharedPreferences
        )
    }

    @Provides
    @Singleton
    fun provideRegistrationRepository(
        api: Api,
        mapper: ResponseMapper,
        sharedPreferences: DataStore<Preferences>
    ): RegistrationRepository {
        return RegistrationRepositoryImpl(
            api,
            mapper,
            sharedPreferences
        )
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        api: Api,
        mapper: ResponseMapper,
        mapperFromJournalListDtoToModel: MapperFromJournalListDtoToModel,
        mapperFromJournalPageDtoToModel: MapperFromJournalPageDtoToModel,
        mapperFromArticlesListToModel: MapperFromArticlesListToModel,
        mapperFromCommentDtoToModel: MapperFromCommentDtoToModel
    ): MainRepository {
        return MainRepositoryImpl(
            api,
            mapper,
            mapperFromJournalListDtoToModel,
            mapperFromJournalPageDtoToModel,
            mapperFromArticlesListToModel,
            mapperFromCommentDtoToModel
        )
    }


    @Provides
    @Singleton
    fun provideResponseMapper(): ResponseMapper {
        return ResponseMapperImpl()
    }

    @Provides
    @Singleton
    fun provideMapperFromAuthorizationCheckDtoToModel(): MapperFromAuthorizationCheckDtoToModel {
        return MapperFromAuthorizationCheckDtoToModelImpl()
    }


    @Provides
    @Singleton
    fun provideMapperFromUserPhoneDtoToModel(): MapperFromUserPhoneDtoToModel {
        return MapperFromUserPhoneDtoToModelImpl()
    }

    @Provides
    @Singleton
    fun provideMapperFromJournalListDtoToModel(): MapperFromJournalListDtoToModel {
        return MapperFromJournalListDtoToModelImpl()
    }

    @Provides
    @Singleton
    fun provideMapperFromJournalPageDtoToModel(
        mapperFromJournalListDtoToModel: MapperFromJournalListDtoToModel,
        mapperFromCommentDtoToModel: MapperFromCommentDtoToModel
    ): MapperFromJournalPageDtoToModel {
        return MapperFromJournalPageDtoToModelImpl(
            mapperFromJournalListDtoToModel,
            mapperFromCommentDtoToModel
        )
    }

    @Provides
    @Singleton
    fun provideMapperArticlesListToModel(
        mapperFromCommentDtoToModel: MapperFromCommentDtoToModel
    ): MapperFromArticlesListToModel {
        return MapperFromArticlesListToModelImpl(mapperFromCommentDtoToModel)
    }

    @Provides
    @Singleton
    fun provideMapperFromCommentDtoToModel(): MapperFromCommentDtoToModel {
        return MapperFromCommentDtoToModelImpl()
    }

}

