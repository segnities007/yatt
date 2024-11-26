package jp.co.yumemi.droidtraining.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.random.Random

@Module
@InstallIn(SingletonComponent::class)
object RandomModule {

    @Provides
    @Singleton
    fun provideRandom(): Random = Random.Default
}
