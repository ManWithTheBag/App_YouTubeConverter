package org.doit.youtubeconverterapp.model.youTubeContent.general;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

public class YouTubeContentModelGeneral {
    @JsonProperty("title")
    String title;
    @JsonProperty("image")
    String image;
    @JsonProperty("download_url")
    String downloadUrl;
    String extensionFile;


    public YouTubeContentModelGeneral() {
    }

    public YouTubeContentModelGeneral(String title, String image, String downloadUrl, String extensionFile) {
        this.title = title;
        this.image = image;
        this.downloadUrl = downloadUrl;
        this.extensionFile = extensionFile;
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

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getExtensionFile() {
        return extensionFile;
    }

    public void setExtensionFile(String extensionFile) {
        this.extensionFile = extensionFile;
    }

    @Override
    public String toString() {
        return "YouTubeContentModelGeneral{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", extensionFile='" + extensionFile + '\'' +
                '}';
    }
}
