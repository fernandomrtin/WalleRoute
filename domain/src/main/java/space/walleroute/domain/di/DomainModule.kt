package space.walleroute.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import space.walleroute.domain.bridge.MainBridge
import space.walleroute.domain.bridge.MainBridgeImpl
import space.walleroute.domain.usecase.GetPlanetInfoUseCase
import space.walleroute.domain.usecase.GetRobotRouteUseCase

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideMainBridge(getPlanetInfoUseCase: GetPlanetInfoUseCase, getRobotRouteUseCase: GetRobotRouteUseCase): MainBridge {
        return MainBridgeImpl(getPlanetInfoUseCase, getRobotRouteUseCase)
    }
}