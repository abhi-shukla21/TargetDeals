package me.abhishek.targetdeals.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.abhishek.targetdeals.repository.DealRepository;
import me.abhishek.targetdeals.viewmodel.factory.DealDetailViewModelFactory;

@Module
public class DealDetailViewModelFactoryModule {

    @Provides
    @Singleton
    DealDetailViewModelFactory provideDealDetailViewMode(DealRepository dealRepository) {
        return new DealDetailViewModelFactory(dealRepository);

    }
}
