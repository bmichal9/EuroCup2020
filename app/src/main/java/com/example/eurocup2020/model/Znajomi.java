package com.example.eurocup2020.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Znajomi {


    @Expose
    @SerializedName("name_znajomego")
    private String name_znajomego;
    @Expose
    @SerializedName("punkty_razem")
    private int punkty_razem;
    @Expose
    @SerializedName("success")
    private Boolean success;
    @Expose
    @SerializedName("message")
    private String message;



    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName_znajomego() {
        return name_znajomego;
    }

    public void setName_znajomego(String name_znajomego) {
        this.name_znajomego = name_znajomego;
    }

    public int getPunkty_razem() {
        return punkty_razem;
    }

    public void setPunkty_razem(int punkty_razem) {
        this.punkty_razem = punkty_razem;
    }
}