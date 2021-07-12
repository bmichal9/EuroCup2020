package com.example.eurocup2020.main.Znajomi;

import com.example.eurocup2020.model.Znajomi;
import java.util.List;

public interface ZnajomiView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Znajomi> znajomis);
    void onErrorLoading(String message);
}
