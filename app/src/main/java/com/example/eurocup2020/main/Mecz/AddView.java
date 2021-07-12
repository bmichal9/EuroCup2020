package com.example.eurocup2020.main.Mecz;

public interface AddView {

    void showProgress();
    void hideProgress();
    void onAddSuccess(String message);
    void onAddError(String message);

}