package org.doit.youtubeconverterapp.model.youTubeContent.step2;

import org.doit.youtubeconverterapp.service.YouTubeAPIService;

import java.io.IOException;

public class ProcessStep2 {

    private int countOfTryStep2 = 20;

    public YouTubeContentModelStep2 getContentModelStep2(String uniqueId) throws IOException, InterruptedException {
        YouTubeAPIService youTubeAPIService = new YouTubeAPIService();
        YouTubeContentModelStep2 contentModelStep2 = new YouTubeContentModelStep2();
        String responseBodyStringStep2;

        Thread.sleep(5000);

        for (int i = 0; i < countOfTryStep2; i++) {
            responseBodyStringStep2 = youTubeAPIService.getYouTubeContentStep2().getJsonModelStep2(uniqueId);

            if(responseBodyStringStep2 == null) return null;

            contentModelStep2 =  youTubeAPIService.getYouTubeContentStep2().getObjModelStep2(responseBodyStringStep2);
            System.out.println("Step1: " + contentModelStep2.toString());

            if (contentModelStep2.getSuccess().equals("1") || contentModelStep2.getSuccess().equals(null)) {
                break;
            }

            Thread.sleep(5000);
            contentModelStep2 = null;
        }

        return contentModelStep2;
    }
}
