package com.example.eurocup2020.main.Mecz;

import com.example.eurocup2020.model.Mecz;
import java.util.List;

public interface MeczView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Mecz> meczes);
    void onErrorLoading(String message);
}
