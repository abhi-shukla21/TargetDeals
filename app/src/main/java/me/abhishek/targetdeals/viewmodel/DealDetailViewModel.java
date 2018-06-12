package me.abhishek.targetdeals.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import me.abhishek.targetdeals.model.Deal;
import me.abhishek.targetdeals.repository.DealRepository;

public class DealDetailViewModel extends ViewModel {

    private LiveData<Deal> deal;
    private DealRepository dealRepository;

    @Inject
    public DealDetailViewModel(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    public void init(String dealId) {
        if (this.deal != null) {
            return;
        }
        deal = dealRepository.getDeal(dealId);
    }

    public LiveData<Deal> getDeal() {
        return deal;
    }
}
