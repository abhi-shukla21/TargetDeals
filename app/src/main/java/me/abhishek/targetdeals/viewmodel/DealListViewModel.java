package me.abhishek.targetdeals.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import me.abhishek.targetdeals.model.Deal;
import me.abhishek.targetdeals.repository.DealRepository;
import me.abhishek.targetdeals.utils.RequestStatus;

public class DealListViewModel extends ViewModel {
    private LiveData<List<Deal>> deals;
    private DealRepository dealRepository;

    @Inject
    public DealListViewModel(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }


    public LiveData<List<Deal>> getDeals(MutableLiveData<RequestStatus> requestStatus) {
        if (deals == null) {
            deals = dealRepository.getDeals(requestStatus);
        }
        return deals;
    }
}
