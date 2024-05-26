package com.example.sapernewvers.web.dto;

public class CheckNameResponse {
    public CheckNameResponse() {

    }

    private boolean nameFree;

    public boolean isNameFree() {
        return nameFree;
    }

    public void setNameFree(boolean nameFree) {
        this.nameFree = nameFree;
    }
}
