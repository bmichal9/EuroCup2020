package com.example.eurocup2020.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Grupa {

    @Expose
    @SerializedName("id_meczu") private int id_meczu;
    @Expose
    @SerializedName("gosp") private String gosp;
    @Expose
    @SerializedName("gosc") private String gosc;
    @Expose
    @SerializedName("wyn_gosp") private int wyn_gosp;
    @Expose
    @SerializedName("wyn_gosc") private int wyn_gosc;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;

    public int getId_meczu() {
        return id_meczu;
    }

    public void setId_meczu(int id_meczu) {
        this.id_meczu = id_meczu;
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

    public int getWyn_gosp() {
        return wyn_gosp;
    }

    public void setWyn_gosp(int wyn_gosp) {
        this.wyn_gosp = wyn_gosp;
    }

    public int getWyn_gosc() {
        return wyn_gosc;
    }

    public void setWyn_gosc(int wyn_gosc) {
        this.wyn_gosc = wyn_gosc;
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
}
