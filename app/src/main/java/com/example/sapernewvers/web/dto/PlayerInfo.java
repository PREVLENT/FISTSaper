package com.example.sapernewvers.web.dto;

public class PlayerInfo {
    public PlayerInfo() {

    }
    private String name;
    private Long score;

    public PlayerInfo(String name, Long score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
