package com.example.kanmeitu.api;

import com.example.kanmeitu.adapter.response.ListResponse;
import com.example.kanmeitu.domain.Image;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Service {
    @GET("v1/images")
  Observable<ListResponse<Image>> images();
}
