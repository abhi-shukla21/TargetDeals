package me.abhishek.targetdeals.di.component;

import javax.inject.Singleton;

import dagger.Component;
import me.abhishek.targetdeals.di.module.AppModule;
import me.abhishek.targetdeals.di.module.DealListViewModelFactoryModule;
import me.abhishek.targetdeals.di.module.RepositoryModule;
import me.abhishek.targetdeals.view.DealListFragment;
import me.abhishek.targetdeals.viewmodel.factory.DealListViewModelFactory;

@Singleton
@Component(modules = {AppModule.class, RepositoryModule.class,
        DealListViewModelFactoryModule.class})
public interface DealListComponent {

    DealListViewModelFactory provideListViewModelFactory();


    void inject(DealListFragment dealDetailFragment);
}
