package com.example.eurocup2020.main.Punkty;

import com.example.eurocup2020.model.Punkty;
import java.util.List;

public interface PunktyView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Punkty> punkties);
    void onErrorLoading(String message);
}
