package org.doit.youtubeconverterapp.model.youTubeContent.step1;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.doit.youtubeconverterapp.utility.CustomObjMapper;
import org.doit.youtubeconverterapp.utility.RapidAPIConnection;


public class YouTubeContentStep1 {

    String rapidApiHost = "youtube-video-downloader-4k-and-8k-mp3.p.rapidapi.com";
    String rapidApiUrl = "https://youtube-video-downloader-4k-and-8k-mp3.p.rapidapi.com/download.php?format=";

    public String getJsonModelStep1(String youTubeUrl, String format){

        rapidApiUrl = rapidApiUrl + format + "&url=" + youTubeUrl;

        RapidAPIConnection rapidAPIConnection = new RapidAPIConnection();
        String responseBodyString = rapidAPIConnection.getResponseBody(rapidApiUrl, rapidApiHost);

        if (responseBodyString == null){
            return null;
        }

        return responseBodyString;
    }

    public YouTubeContentModelStep1 getObjModelStep1(String responseBodyString) throws JsonProcessingException {
        CustomObjMapper<YouTubeContentModelStep1> customObjMapper = new CustomObjMapper<YouTubeContentModelStep1>(YouTubeContentModelStep1.class);
        YouTubeContentModelStep1 contentModelStep1 = customObjMapper.getObjFromJson(responseBodyString);

        return contentModelStep1;
    }
}
