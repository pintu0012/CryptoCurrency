package com.example.cryptocurrency.di

import android.content.Context
import com.example.cryptocurrency.presentation.utils.CoroutineContextProvider
import com.example.cryptocurrency.presentation.utils.CoroutineContextProviderImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

//    @Provides
//    @Singleton
//    fun providePresentationPreferenceHelper(@ApplicationContext context: Context): PresentationPreferencesHelper {
//        return PresentationPreferencesHelper(context)
//    }

    @Provides
    @Singleton
    fun provideCoroutineContextProvider(contextProvider: CoroutineContextProviderImp): CoroutineContextProvider =
        contextProvider
}
