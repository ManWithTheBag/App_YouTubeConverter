package org.doit.youtubeconverterapp.utility;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Format {
    private LinkedHashMap<String, String> formatMap;

    public Format(){
        setFormatMap();
    }

    public LinkedHashMap<String, String> getFormatMap(){
        return formatMap;
    }

    private void setFormatMap(){
        formatMap = new LinkedHashMap<>();
        formatMap.put("MP3", "mp3");
        formatMap.put("MP4 360", "360");
        formatMap.put("MP4 480", "480");
        formatMap.put("MP4 720", "720");
        formatMap.put("MP4 1080", "1080");
    }
}
