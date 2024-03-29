package app.revanced.integrations.youtube.patches.video;

import java.io.Serializable;

public class VideoChannel implements Serializable {
    private String author;
    private String channelId;

    public VideoChannel(String author, String channelId) {
        this.author = author;
        this.channelId = channelId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}