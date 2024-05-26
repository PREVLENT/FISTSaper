package com.example.sapernewvers.web.dto;

import java.util.List;

public class TopPlayerResponse {
    public TopPlayerResponse(){
    }
    private List<PlayerInfo> playerInfoList;

    public TopPlayerResponse(List<PlayerInfo> playerInfoList) {
        this.playerInfoList = playerInfoList;
    }

    public List<PlayerInfo> getPlayerInfoList() {
        return playerInfoList;
    }

    public void setPlayerInfoList(List<PlayerInfo> playerInfoList) {
        this.playerInfoList = playerInfoList;
    }
}
