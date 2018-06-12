package me.abhishek.targetdeals.viewmodel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import me.abhishek.targetdeals.repository.DealRepository;
import me.abhishek.targetdeals.viewmodel.DealDetailViewModel;

public class DealDetailViewModelFactory implements ViewModelProvider.Factory {

    private DealRepository dealRepository;

    @Inject
    public DealDetailViewModelFactory(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    @NonNull
    @Override
    public ViewModel create(@NonNull Class modelClass) {
        return new DealDetailViewModel(dealRepository);
    }
}
