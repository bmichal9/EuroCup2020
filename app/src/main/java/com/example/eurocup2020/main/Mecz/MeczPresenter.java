package com.example.eurocup2020.main.Mecz;

import androidx.annotation.NonNull;

import com.example.eurocup2020.api.ApiClient;
import com.example.eurocup2020.api.ApiInterface;
import com.example.eurocup2020.model.Mecz;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeczPresenter {

    private MeczView view;

    public MeczPresenter(MeczView view){
        this.view=view;
    }

    public void getData(int pomoc2) {

        int idd2=pomoc2;

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Mecz>> call= apiInterface.getMeczes(idd2);
        call.enqueue(new Callback<List<Mecz>>() {

            @Override
            public void onResponse(@NonNull Call<List<Mecz>> call, @NonNull Response<List<Mecz>> response) {
                view.hideLoading();

                if(response.isSuccessful() && response.body() !=null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Mecz>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}

