package me.abhishek.targetdeals.webservice;

import me.abhishek.targetdeals.model.DealList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DealService {


    @GET("/api/deals")
    Call<DealList> getDeals();
}
