package org.doit.youtubeconverterapp.model.youTubeContent.general;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.doit.youtubeconverterapp.utility.CustomObjMapper;


public class YouTubeContentGeneral {

    public YouTubeContentModelGeneral getObjModelGeneral(String responseBodyString) throws JsonProcessingException {
        CustomObjMapper<YouTubeContentModelGeneral> customObjMapper = new CustomObjMapper<YouTubeContentModelGeneral>(YouTubeContentModelGeneral.class);
        YouTubeContentModelGeneral contentModelGeneral = customObjMapper.getObjFromJson(responseBodyString);

        return contentModelGeneral;
    }
}
