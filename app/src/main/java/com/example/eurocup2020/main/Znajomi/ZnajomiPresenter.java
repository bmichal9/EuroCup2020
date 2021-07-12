package com.example.eurocup2020.main.Znajomi;

import androidx.annotation.NonNull;

import com.example.eurocup2020.api.ApiClient;
import com.example.eurocup2020.api.ApiInterface;
import com.example.eurocup2020.model.Znajomi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ZnajomiPresenter {

    private ZnajomiView view;

    public ZnajomiPresenter(ZnajomiView view){
        this.view=view;
    }

    public void getListeZnajomych(int pomoc) {

        int iddd=pomoc;

        view.showLoading();

        ApiInterface apiInterfaceZnajomi = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Znajomi>> call= apiInterfaceZnajomi.getZnajomiList(iddd);
        call.enqueue(new Callback<List<Znajomi>>() {

            @Override
            public void onResponse(@NonNull Call<List<Znajomi>> call, @NonNull Response<List<Znajomi>> response) {
                view.hideLoading();

                if(response.isSuccessful() && response.body() !=null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Znajomi>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}

