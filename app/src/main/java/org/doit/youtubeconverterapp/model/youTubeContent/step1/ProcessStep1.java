package org.doit.youtubeconverterapp.model.youTubeContent.step1;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.doit.youtubeconverterapp.service.YouTubeAPIService;
import org.doit.youtubeconverterapp.utility.Format;

public class ProcessStep1 {

    private int countOfTryStep1 = 5;

    public YouTubeContentModelStep1 getContentModelStep1(String youTubeUrl, String formatValue) throws InterruptedException, JsonProcessingException {
        YouTubeAPIService youTubeAPIService = new YouTubeAPIService();
        YouTubeContentModelStep1 contentModelStep1 = new YouTubeContentModelStep1();
        String responseBodyStringStep1;

        for (int i = 0; i < countOfTryStep1; i++) {
            responseBodyStringStep1 = youTubeAPIService.getYouTubeContentStep1().getJsonModelStep1(youTubeUrl, formatValue);

            if(responseBodyStringStep1 == null) return  null;

            contentModelStep1 = youTubeAPIService.getYouTubeContentStep1().getObjModelStep1(responseBodyStringStep1);

            Thread.sleep(500);
            System.out.println("Step1: " + contentModelStep1.getSuccess());

            if (contentModelStep1.getSuccess().equals("true") || contentModelStep1.getSuccess().equals(null)) {
                break;
            }

            Thread.sleep(4000);
            contentModelStep1 = null;
        }

        if(contentModelStep1 != null)
        {
            contentModelStep1.setExtensionFile(getExtensionFile(formatValue));
        }

        return  contentModelStep1;
    }

    private String getExtensionFile(String formatValue){
        if(formatValue == "mp3"){
            return ".mp3";
        }else {
            return ".mp4";
        }
    }

}
