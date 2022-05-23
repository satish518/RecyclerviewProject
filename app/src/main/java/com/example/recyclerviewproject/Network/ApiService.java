package com.example.recyclerviewproject.Network;

import com.sample.lifecycleawarecomponents.RecyclerviewRetrofitMvvm.Model.TestingModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("cats.json")
    Call<TestingModel> getCatList();
}


