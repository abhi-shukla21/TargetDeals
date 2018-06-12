package me.abhishek.targetdeals.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.abhishek.targetdeals.repository.DealRepository;
import me.abhishek.targetdeals.viewmodel.factory.DealListViewModelFactory;

@Module
public class DealListViewModelFactoryModule {
    @Provides
    @Singleton
    DealListViewModelFactory provideDealListViewMode(DealRepository dealRepository) {
        return new DealListViewModelFactory(dealRepository);

    }
}
