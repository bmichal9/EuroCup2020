package com.example.eurocup2020.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mecz {

    @Expose
    @SerializedName("id_meczu") private int id_meczu;
    @Expose
    @SerializedName("gosp") private String gosp;
    @Expose
    @SerializedName("gosc") private String gosc;
    @Expose
    @SerializedName("grupa") private String grupa;
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

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }
}
