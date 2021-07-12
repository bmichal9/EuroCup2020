package com.example.eurocup2020.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewMecz {

    @Expose
    @SerializedName("id_user")
    private int id_user;
    @Expose
    @SerializedName("id_meczu")
    private int id_meczu;
    @Expose
    @SerializedName("gosp")
    private String gosp;
    @Expose
    @SerializedName("gosc")
    private String gosc;
    @Expose
    @SerializedName("bet_gosp")
    private int bet_gosp;
    @Expose
    @SerializedName("bet_gosc")
    private int bet_gosc;
    @Expose
    @SerializedName("success")
    private Boolean success;
    @Expose
    @SerializedName("message")
    private String message;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_meczu() {
        return id_meczu;
    }

    public void setId_meczu(int id_meczu) {
        this.id_meczu = id_meczu;
    }

    public int getBet_gosp() {
        return bet_gosp;
    }

    public void setBet_gosp(int bet_gosp) {
        this.bet_gosp = bet_gosp;
    }

    public int getBet_gosc() {
        return bet_gosc;
    }

    public void setBet_gosc(int bet_gosc) {
        this.bet_gosc = bet_gosc;
    }

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

    public String getGosp() {
        return gosp;
    }

    public void setGosp(String gosp) {
        this.gosp = gosp;
    }

    public String getGosc() {
        return gosc;
    }

    public void setGosc(String gosc) {
        this.gosc = gosc;
    }
}