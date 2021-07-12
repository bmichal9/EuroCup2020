package com.example.eurocup2020.main.Tabela;

import com.example.eurocup2020.model.Grupa;
import java.util.List;

public interface TabelaView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Grupa> groups);
    void onErrorLoading(String message);
}
