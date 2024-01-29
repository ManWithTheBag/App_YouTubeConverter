package org.doit.youtubeconverterapp.service;


import org.doit.youtubeconverterapp.model.youTubeContent.general.YouTubeContentGeneral;
import org.doit.youtubeconverterapp.model.youTubeContent.step1.YouTubeContentStep1;
import org.doit.youtubeconverterapp.model.youTubeContent.step2.YouTubeContentStep2;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class YouTubeAPIService {

    public YouTubeContentStep1 getYouTubeContentStep1(){
        return new YouTubeContentStep1();
    }
    public YouTubeContentStep2 getYouTubeContentStep2(){
        return new YouTubeContentStep2();
    }
    public YouTubeContentGeneral getYouTubeContentGeneral(){
        return new YouTubeContentGeneral();
    }
}
