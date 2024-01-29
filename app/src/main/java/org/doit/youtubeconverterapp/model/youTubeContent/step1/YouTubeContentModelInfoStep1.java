package org.doit.youtubeconverterapp.model.youTubeContent.step1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

public class YouTubeContentModelInfoStep1 {
    @JsonProperty("title")
    String title;

    @JsonProperty("image")
    String image;

    public YouTubeContentModelInfoStep1() {
    }

    public YouTubeContentModelInfoStep1(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "YouTubeContentModelInfoStep1{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
