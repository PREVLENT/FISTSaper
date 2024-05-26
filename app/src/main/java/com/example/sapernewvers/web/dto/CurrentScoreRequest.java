package com.example.sapernewvers.web.dto;

public class CurrentScoreRequest {
    public CurrentScoreRequest(String name) {
        this.name = name;
    }

    public CurrentScoreRequest() {

    }
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
