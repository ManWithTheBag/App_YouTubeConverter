package org.doit.youtubeconverterapp.model.youTubeContent.step2;

import com.fasterxml.jackson.core.JsonProcessingException;


import org.doit.youtubeconverterapp.utility.CustomObjMapper;
import org.doit.youtubeconverterapp.utility.RapidAPIConnection;

import java.io.IOException;

public class YouTubeContentStep2 {

    String rapidApiHost = "youtube-video-downloader-4k-and-8k-mp3.p.rapidapi.com";
    String rapidApiUrl = "https://youtube-video-downloader-4k-and-8k-mp3.p.rapidapi.com/progress.php?id=";

    public String getJsonModelStep2(String uniqueId) throws IOException {

        rapidApiUrl += uniqueId;

        RapidAPIConnection rapidAPIConnection = new RapidAPIConnection();
        String responseBodyString = rapidAPIConnection.getResponseBody(rapidApiUrl, rapidApiHost);

        if (responseBodyString == null){
            return null;
        }

        return responseBodyString;
    }

    public YouTubeContentModelStep2 getObjModelStep2(String responseBodyString) throws JsonProcessingException {
        CustomObjMapper<YouTubeContentModelStep2> customObjMapper = new CustomObjMapper<YouTubeContentModelStep2>(YouTubeContentModelStep2.class);
        YouTubeContentModelStep2 contentModelStep2 = customObjMapper.getObjFromJson(responseBodyString);

        return contentModelStep2;
    }
}
