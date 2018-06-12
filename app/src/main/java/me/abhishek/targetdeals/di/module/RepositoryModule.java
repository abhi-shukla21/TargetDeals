package me.abhishek.targetdeals.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.abhishek.targetdeals.db.DealDao;
import me.abhishek.targetdeals.db.DealDatabase;
import me.abhishek.targetdeals.repository.DealRepository;
import me.abhishek.targetdeals.webservice.DealService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RepositoryModule {


    private DealDatabase mDatabase;

    public RepositoryModule(Application application) {
        mDatabase = Room.databaseBuilder(application, DealDatabase.class, DealDatabase.DB_NAME).build();
    }


    @Provides
    @Singleton
    DealDatabase provideRoomDatabase() {
        return mDatabase;
    }

    @Provides
    @Singleton
    DealDao provideDealDao(DealDatabase dealsDatabase) {
        return dealsDatabase.dealDao();
    }

    @Provides
    @Singleton
    DealService provideDealService(Retrofit retrofit) {
        DealService dealService = retrofit.create(DealService.class);
        return dealService;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@Named("baseUrl") String baseUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory
                (GsonConverterFactory.create()).client(client.build()).build();
        return retrofit;
    }

    @Provides
    @Named("baseUrl")
    String provideBaseUrl() {
        return "http://target-deals.herokuapp.com/";
    }


    @Provides
    DealRepository provideDealRepository(DealService dealService, DealDao dealDao) {
        return new DealRepository(dealService, dealDao);
    }


}
