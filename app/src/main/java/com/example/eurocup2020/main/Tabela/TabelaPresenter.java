package com.example.eurocup2020.main.Tabela;

import androidx.annotation.NonNull;

import com.example.eurocup2020.api.ApiClient;
import com.example.eurocup2020.api.ApiInterface;
import com.example.eurocup2020.model.Grupa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabelaPresenter {

    private TabelaView view;

    public TabelaPresenter(TabelaView view){
        this.view=view;
    }

    public void getData() {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Grupa>> call= apiInterface.getGrupaList();
        call.enqueue(new Callback<List<Grupa>>() {

            @Override
            public void onResponse(@NonNull Call<List<Grupa>> call, @NonNull Response<List<Grupa>> response) {
                view.hideLoading();

                if(response.isSuccessful() && response.body() !=null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Grupa>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}

