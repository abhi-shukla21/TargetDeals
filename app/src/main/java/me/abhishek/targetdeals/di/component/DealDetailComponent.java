package me.abhishek.targetdeals.di.component;

import javax.inject.Singleton;

import dagger.Component;
import me.abhishek.targetdeals.di.module.AppModule;
import me.abhishek.targetdeals.di.module.DealDetailViewModelFactoryModule;
import me.abhishek.targetdeals.di.module.RepositoryModule;
import me.abhishek.targetdeals.view.DealDetailFragment;
import me.abhishek.targetdeals.viewmodel.factory.DealDetailViewModelFactory;

@Singleton
@Component(modules = {AppModule.class, RepositoryModule.class,
        DealDetailViewModelFactoryModule.class})
public interface DealDetailComponent {
    DealDetailViewModelFactory provideDetailViewModelFactory();


    void inject(DealDetailFragment dealDetailFragment);
}

