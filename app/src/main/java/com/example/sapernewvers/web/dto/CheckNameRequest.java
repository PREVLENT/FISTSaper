package com.example.sapernewvers.web.dto;

public class CheckNameRequest {
    public CheckNameRequest() {

    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CheckNameRequest(String name) {
        this.name = name;
    }
}
