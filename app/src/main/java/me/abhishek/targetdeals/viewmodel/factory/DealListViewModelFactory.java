package me.abhishek.targetdeals.viewmodel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import me.abhishek.targetdeals.repository.DealRepository;
import me.abhishek.targetdeals.viewmodel.DealListViewModel;

public class DealListViewModelFactory implements ViewModelProvider.Factory {

    private DealRepository dealRepository;

    @Inject
    public DealListViewModelFactory(DealRepository repository) {
        dealRepository = repository;
    }

    @NonNull
    @Override
    public ViewModel create(@NonNull Class modelClass) {
        return new DealListViewModel(dealRepository);
    }
}
