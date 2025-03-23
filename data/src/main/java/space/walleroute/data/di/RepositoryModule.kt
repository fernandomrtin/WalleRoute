package space.walleroute.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import space.walleroute.data.repository.MainRepositoryImpl
import space.walleroute.domain.contract.MainRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
}