package com.example.eurocup2020.main.Mecz;

import androidx.annotation.NonNull;

import com.example.eurocup2020.api.ApiClient;
import com.example.eurocup2020.api.ApiInterface;
import com.example.eurocup2020.model.NewMecz;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPresenter {

    private AddView view;

    AddPresenter(AddView view) {
        this.view = view;
    }

    void saveMecz(final int id_user, final int id_meczu, final String gosp, final String gosc, final int bet_gosp, final int bet_gosc) {

        view.showProgress();

        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<NewMecz> call = apiInterface.saveMecz(id_user, id_meczu, gosp, gosc, bet_gosp, bet_gosc);

        call.enqueue(new Callback<NewMecz>() {
            @Override
            public void onResponse(@NonNull Call<NewMecz> call, @NonNull Response<NewMecz> response) {
                view.hideProgress();

                if(response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSuccess();

                    if(success) {
                        view.onAddSuccess(response.body().getMessage());
                    }
                    else {
                        view.onAddError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewMecz> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onAddError(t.getLocalizedMessage());
            }
        });
    }
}

