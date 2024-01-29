package org.doit.youtubeconverterapp.utility;
import android.os.AsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RapidAPIConnection {


    public String getResponseBody(String youTubeUrl, String rapidApiHost) {

        OkHttpClient client = new OkHttpClient();

        String key = "c488fccba2msh41081a0f6502205p18c7b1jsn667592036dda";

        Request request = new Request.Builder()
                .url(youTubeUrl)
                .get()
                .addHeader("X-RapidAPI-Key", key)
                .addHeader("X-RapidAPI-Host", rapidApiHost)
                .build();


        try {
            Response response = client.newCall(request).execute();

            if(!checkSuccessCode(response)){
                return null;
            }

            String responseBodyString = response.body().string();
            return  responseBodyString;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkSuccessCode(Response response){
        if(response.code() == 200){
            return true;
        }
        return false;
    }


    public String getIdFromUrl(String youTubeUrl){
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youTubeUrl);

        if(matcher.find()){
            return matcher.group();
        } else {
            return "error";
        }
    }


}
