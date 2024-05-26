package com.example.sapernewvers.web;

import android.util.Log;

import com.example.sapernewvers.web.dto.CheckNameRequest;
import com.example.sapernewvers.web.dto.CheckNameResponse;
import com.example.sapernewvers.web.dto.CurrentScoreRequest;
import com.example.sapernewvers.web.dto.CurrentScoreResponse;
import com.example.sapernewvers.web.dto.ShareScoreRequest;
import com.example.sapernewvers.web.dto.TopPlayerResponse;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserService {
    private static final String URL = "https://f88e-87-76-3-29.ngrok-free.app";

    public static boolean isNameFree(String name) {
        CheckNameRequest request = new CheckNameRequest(name);
        Gson gson = new Gson();
        String requestAsJson = gson.toJson(request);
        Log.e("UserSerivce", requestAsJson);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), requestAsJson);
        Request requestR = new Request.Builder().url(URL + "/checkName").post(requestBody).build();
        try {
            Response response = client.newCall(requestR).execute();
            if (response.isSuccessful()) {
                String responseAsJson = response.body().string();
                CheckNameResponse checkNameResponse = gson.fromJson(responseAsJson, CheckNameResponse.class);
                return checkNameResponse.isNameFree();
            } else return false;
        } catch (IOException e) {
            Log.d("MYRESPONSE", e.toString());
            return false;
        }
    }

    public static Long getCurrentScoreByName(String name) {
        CurrentScoreRequest request = new CurrentScoreRequest(name);
        Gson gson = new Gson();
        String requestAsJson = gson.toJson(request);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), requestAsJson);
        Request requestR = new Request.Builder().url(URL + "/score").post(requestBody).build();
        try {
            Response response = client.newCall(requestR).execute();
            if (response.isSuccessful()) {
                String responseAsJson = response.body().string();
                CurrentScoreResponse currentScoreResponse = gson.fromJson(responseAsJson, CurrentScoreResponse.class);
                return currentScoreResponse.getScore();
            } else {
                throw new RuntimeException("Не удалось выполнить запрос:" + response.code());
            }
        } catch (IOException e) {
            Log.d("MYRESPONSE", e.toString());
            throw new RuntimeException("Не удалось выполнить запрос");
        }
    }

    public static void shareScore(String name, Long score) {
        ShareScoreRequest request = new ShareScoreRequest(name, score);
        Gson gson = new Gson();
        String requestAsJson = gson.toJson(request);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), requestAsJson);
        Request requestR = new Request.Builder().url(URL + "/shareScore").post(requestBody).build();
        try {
            Response response = client.newCall(requestR).execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException("Не удалось выполнить запрос:" + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось выполнить запрос");
        }
    }

    public static TopPlayerResponse getTopPlayer() {
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        Request requestR = new Request.Builder().url(URL + "/topPlayer").get().build();
        try {
            Response response = client.newCall(requestR).execute();
            if (response.isSuccessful()) {
                String responseAsJson = response.body().string();
                TopPlayerResponse topPlayerResponse = gson.fromJson(responseAsJson, TopPlayerResponse.class);
                return topPlayerResponse;
            } else throw new RuntimeException("Не удалось выполнить запрос:" + response.code());
        } catch (IOException e) {
            Log.d("MYRESPONSE", e.toString());
            throw new RuntimeException("Не удалось выполнить запрос");
        }
    }
//    public static void addUser(User user){
//        OkHttpClient client = new OkHttpClient();
//        String json = String.format("{\"name\":\"%s\",\"point\":%d}",user.getName(),user.getPoints());
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
//        Request request = new Request.Builder().url(URL+"/addUser").post(requestBody).build();
//        try {
//            Response response = client.newCall(request).execute();
//        } catch (IOException e) {
//            Log.d("MYRESPONSE",e.toString());
//        }
//    }
//    public static void updatePoint(User user){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient client = new OkHttpClient();
//                String json = String.format("{\"name\":\"%s\",\"point\":%d}",user.getName(),user.getPoints());
//                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
//                Request request = new Request.Builder().url(URL+"/updatePoints").post(requestBody).build();
//                try {
//                    Response response = client.newCall(request).execute();
//                } catch (IOException e) {
//                    Log.d("MYRESPONSE",e.toString());
//                }
//            }
//        }).start();
//    }
//
//    public static boolean check(String name){
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(String.format("%s/checkName?name=%s", URL, name)).build();
//        try{
//            Response response = client.newCall(request).execute();
//            return response.body().string().equals("false");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static List<User> getAll(){
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(URL + "/getAll").build();
//        try (Response response = client.newCall(request).execute()) {
//            List<User> users = stringToArray(response.body().string(), User[].class);
//            return users;
//        } catch (IOException e) {
//            Log.d("MYRESPONSE",e.toString());
//        }
//        return null;
//    }
//
//    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
//        T[] arr = new Gson().fromJson(s, clazz);
//        return Arrays.asList(new Gson().fromJson(s, clazz));
//    }
}
