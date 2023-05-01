package com.esoume.coding.dictionaryapp.di

import android.app.Application
import androidx.room.Room
import com.esoume.coding.dictionaryapp.data.local.db.WordInfoDatabase
import com.esoume.coding.dictionaryapp.data.remote.api.DictionaryApi
import com.esoume.coding.dictionaryapp.data.repository.WordInfoRepositoryImpl
import com.esoume.coding.dictionaryapp.domain.repository.WordInfoRepository
import com.esoume.coding.dictionaryapp.domain.usecase.GetWordInfoUseCase
import com.esoume.coding.dictionaryapp.utils.Constants
import com.esoume.coding.dictionaryapp.utils.Converters
import com.esoume.coding.dictionaryapp.utils.GsonParser
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWordInfoDataBase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            "word_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        api: DictionaryApi,
        db: WordInfoDatabase
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(api, db.wordInfoDao())
    }

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfoUseCase{
        return GetWordInfoUseCase(repository)
    }

}