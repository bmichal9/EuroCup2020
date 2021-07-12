package com.example.eurocup2020.main.Punkty;

import androidx.annotation.NonNull;

import com.example.eurocup2020.api.ApiClient;
import com.example.eurocup2020.api.ApiInterface;
import com.example.eurocup2020.model.Punkty;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PunktyPresenter {

    private PunktyView view;

    public PunktyPresenter(PunktyView view){
        this.view=view;
    }

    public void getListePunktow(String pomoc) {

        String iddd=pomoc;

        view.showLoading();

        ApiInterface apiInterfaceZnajomi = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Punkty>> call= apiInterfaceZnajomi.getPointList(iddd);
        call.enqueue(new Callback<List<Punkty>>() {

            @Override
            public void onResponse(@NonNull Call<List<Punkty>> call, @NonNull Response<List<Punkty>> response) {
                view.hideLoading();

                if(response.isSuccessful() && response.body() !=null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Punkty>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}

