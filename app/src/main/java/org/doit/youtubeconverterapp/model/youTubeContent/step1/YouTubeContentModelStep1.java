package org.doit.youtubeconverterapp.model.youTubeContent.step1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

public class YouTubeContentModelStep1 {
    @JsonProperty("success")
    String success;
    @JsonProperty("id")
    String uniqueId;
    @JsonProperty("info")
    YouTubeContentModelInfoStep1 youTubeContentModelInfoStep1;

    String extensionFile;

    public YouTubeContentModelStep1(String success, String uniqueId, YouTubeContentModelInfoStep1 youTubeContentModelInfoStep1, String extensionFile) {
        this.success = success;
        this.uniqueId = uniqueId;
        this.youTubeContentModelInfoStep1 = youTubeContentModelInfoStep1;
        this.extensionFile = extensionFile;
    }

    public YouTubeContentModelStep1() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public YouTubeContentModelInfoStep1 getYouTubeContentModelInfoStep1() {
        return youTubeContentModelInfoStep1;
    }

    public void setYouTubeContentModelInfoStep1(YouTubeContentModelInfoStep1 youTubeContentModelInfoStep1) {
        this.youTubeContentModelInfoStep1 = youTubeContentModelInfoStep1;
    }
    public String getExtensionFile() {
        return extensionFile;
    }

    public void setExtensionFile(String extensionFile) {
        this.extensionFile = extensionFile;
    }

    @Override
    public String toString() {
        return "YouTubeContentModelStep1{" +
                "success='" + success + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                ", youTubeContentModelInfoStep1=" + youTubeContentModelInfoStep1 +
                ", extensionFile='" + extensionFile + '\'' +
                '}';
    }
}
