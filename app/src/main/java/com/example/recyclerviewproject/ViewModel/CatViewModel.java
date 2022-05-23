package com.example.recyclerviewproject.ViewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recyclerviewproject.Network.RetroInstance;
import com.example.recyclerviewproject.Utility.LoadingScreen;
import com.sample.lifecycleawarecomponents.RecyclerviewRetrofitMvvm.Model.TestingModel;
import com.sample.lifecycleawarecomponents.RecyclerviewRetrofitMvvm.Model.TestingModelItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatViewModel extends ViewModel {
    private MutableLiveData<List<TestingModelItem>> catList;

    public CatViewModel() {
        catList = new MutableLiveData<>();
    }

    public MutableLiveData<List<TestingModelItem>> getCatListObserver() {
        return catList;
    }

    public void catApiCall(Context context) {

        LoadingScreen.INSTANCE.displayLoading(context, "Loading. Please Wait....", false);

//        ApiService apiService = RetroInstance.getRetrofitContent().create(ApiService.class);
//        Call<TestingModel> call = apiService.getCocktailList();

        Call<TestingModel> call = RetroInstance.initializeApiService().getCatList();
        call.enqueue(new Callback<TestingModel>() {
            @Override
            public void onResponse(Call<TestingModel> call, Response<TestingModel> response) {

                if (response.body() != null) {
                    catList.postValue(response.body());
                } else {
                    Log.e("Error: ", response.code() + " " + response.message());
                }

                LoadingScreen.INSTANCE.hideLoading();
            }

            @Override
            public void onFailure(Call<TestingModel> call, Throwable t) {
                t.getLocalizedMessage();
                catList.setValue(null);
            }
        });
    }
}
