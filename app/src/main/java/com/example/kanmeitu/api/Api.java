package com.example.kanmeitu.api;


import com.example.kanmeitu.adapter.response.ListResponse;
import com.example.kanmeitu.domain.Image;
import com.example.kanmeitu.util.Constants;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Api instance;
    private static Service service;

    private   Api(){
      OkHttpClient.Builder builder = new OkHttpClient.Builder();
      Retrofit retrofit = new Retrofit.Builder()
              .client(builder.build())
              .baseUrl(Constants.ENDPOINT)
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
              .addConverterFactory(GsonConverterFactory.create())
              .build();
      service = retrofit.create(Service.class);
  }
    public static Api getInstance(){
        if (instance==null){
            instance=new Api();
        }
        return instance;

    }
    public Observable<ListResponse<Image>> images(){
        return service.images();
    }

}
