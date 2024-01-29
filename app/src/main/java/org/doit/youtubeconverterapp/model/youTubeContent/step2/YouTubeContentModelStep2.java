package org.doit.youtubeconverterapp.model.youTubeContent.step2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

public class YouTubeContentModelStep2 {
    @JsonProperty("success")
    String success;
    @JsonProperty("progress")
    String progress;
    @JsonProperty("download_url")
    String downloadUrl;

    public YouTubeContentModelStep2() {
    }

    public YouTubeContentModelStep2(String success, String progress, String downloadUrl) {
        this.success = success;
        this.progress = progress;
        this.downloadUrl = downloadUrl;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Override
    public String toString() {
        return "YouTubeContentModelStep2{" +
                "success='" + success + '\'' +
                ", progress='" + progress + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }
}
