package com.example.pokemonapitest.api_controller;

import com.example.pokemonapitest.AppConstants;
import com.example.pokemonapitest.api_controller.api_adapter.NamedApiAdapter;
import com.example.pokemonapitest.api_model.common.NamedApiResponse;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonApi {
    private static volatile Retrofit retrofitInstance;
    private static final Object sLock = new Object();

    public static Retrofit getRetrofitInstance() {
        if (retrofitInstance == null) {
            synchronized (sLock) {
                if (retrofitInstance == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    retrofitInstance = new Retrofit.Builder()
                            .baseUrl(AppConstants.BASE_API_URL)
                            .client(builder.build())
                            .addConverterFactory(GsonConverterFactory.create(
                                    new GsonBuilder()
                                    .registerTypeAdapter(NamedApiResponse.class, new NamedApiAdapter())
                                    .create()
                            ))
                            .build();
                }
            }
        }
        return retrofitInstance;
    }
}
