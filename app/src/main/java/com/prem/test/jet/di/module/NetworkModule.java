package com.prem.test.jet.di.module;

import android.content.Context;

import com.prem.test.jet.network.ApiInterface;
import com.prem.test.jet.network.NetworkObserver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by prem on 28/02/2018.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    OkHttpClient provideHttpClient() {
        return new OkHttpClient().newBuilder().build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://www.mocky.io/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())).build();
        return retrofit;
    }

    @Provides
    @Singleton
    ApiInterface provideNetworkService(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @Singleton
    NetworkObserver provideNetworkObserver(Context context) {
        return new NetworkObserver(context);
    }

}