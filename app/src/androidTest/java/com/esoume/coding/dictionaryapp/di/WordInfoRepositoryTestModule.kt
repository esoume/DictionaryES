package com.esoume.coding.dictionaryapp.di

import com.esoume.coding.dictionaryapp.domain.repository.FakeSuccessWordInfoRepository
import com.esoume.coding.dictionaryapp.domain.repository.WordInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [WordInfoModule::class]
)
abstract class WordInfoRepositoryTestModule {

    @Singleton
    @Binds
    abstract fun bindFakeSuccessWordInfoRepository(
        fakeSuccessWordInfoRepository: FakeSuccessWordInfoRepository
    ): WordInfoRepository
}