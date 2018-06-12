package me.abhishek.targetdeals.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import me.abhishek.targetdeals.db.DealDao;
import me.abhishek.targetdeals.model.Deal;
import me.abhishek.targetdeals.model.DealList;
import me.abhishek.targetdeals.utils.RequestStatus;
import me.abhishek.targetdeals.webservice.DealService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DealRepository {

    private DealService dealService;
    private DealDao dealDao;

    @Inject
    public DealRepository(DealService dealService, DealDao dealDao) {
        this.dealService = dealService;
        this.dealDao = dealDao;
    }

    public LiveData<List<Deal>> getDeals(MutableLiveData<RequestStatus> status) {
        refreshDeals(new WeakReference<>(status));
        return dealDao.getAll();

    }

    public LiveData<Deal> getDeal(String dealId) {
        return dealDao.get(dealId);
    }

    private void refreshDeals(final WeakReference<MutableLiveData<RequestStatus>> status) {
        dealService.getDeals().enqueue(new Callback<DealList>() {
            @Override
            public void onResponse(Call<DealList> call, final Response<DealList> response) {
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        dealDao.insertAll(response.body().getData());
                        MutableLiveData<RequestStatus> requestStatus = status.get();
                        if (requestStatus != null) {
                            requestStatus.postValue(RequestStatus.SUCCESS);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<DealList> call, Throwable t) {
                MutableLiveData<RequestStatus> requestStatus = status.get();
                if (requestStatus != null) {
                    requestStatus.postValue(RequestStatus.FAILED);
                }
            }
        });
    }


}
