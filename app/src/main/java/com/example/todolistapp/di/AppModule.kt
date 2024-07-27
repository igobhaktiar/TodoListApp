package com.example.todolistapp.di

import com.example.todolistapp.data.remote.AuthRepositoryImpl
import com.example.todolistapp.domain.repository.AuthRepository
import com.example.todolistapp.domain.usecase.UserUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): AuthRepository =
        AuthRepositoryImpl(firebaseAuth, firestore)

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: AuthRepository): UserUseCase {
        return UserUseCase(authRepository)
    }

}