package com.example.todolistapp.di

import android.content.Context
import com.example.todolistapp.data.repository.AuthRepositoryImpl
import com.example.todolistapp.data.repository.UserRepositoryImpl
import com.example.todolistapp.data.source.local.SharedPreferenceHelper
import com.example.todolistapp.domain.repository.AuthRepository
import com.example.todolistapp.domain.repository.UserRepository
import com.example.todolistapp.domain.usecase.AuthUseCase
import com.example.todolistapp.domain.usecase.UserUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideFirebaseFireStore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore,
        userUseCase: UserUseCase,
        sharedPreferenceHelper: SharedPreferenceHelper
    ): AuthRepository =
        AuthRepositoryImpl(firebaseAuth, firestore, userUseCase, sharedPreferenceHelper)

    @Provides
    @Singleton
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase {
        return AuthUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        firestore: FirebaseFirestore
    ): UserRepository = UserRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun provideUserUseCase(userRepository: UserRepository): UserUseCase {
        return UserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferenceHelper {
        return SharedPreferenceHelper(context)
    }

}