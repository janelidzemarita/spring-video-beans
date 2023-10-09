package com.epam.rd.autotasks.confbeans.video;

import java.util.Objects;
import java.util.stream.Stream;

public class ChannelWithStudio extends Channel {

    private final VideoStudioImpl videoStudio;

    public ChannelWithStudio(VideoStudio videoStudio) {
        this.videoStudio = (VideoStudioImpl) videoStudio;
    }

    public Video produce() {
        Video video = new Video(videoStudio.getFranchiseName(), videoStudio.produce().getPubTime());
        this.addVideo(video);
        return video;
    }

    @Override
    public Stream<Video> videos() {
        for (int i = 0; i < videoStudio.getFranchiseSize() ; i++) {
            this.produce();
        }
        return super.videos().skip(super.videos().count() - videoStudio.getFranchiseSize());
    }

    @Override
    public String toString() {
        return "ChannelWithStudio{" +
                "videoStudio=" + videoStudio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChannelWithStudio)) return false;
        if (!super.equals(o)) return false;

        ChannelWithStudio that = (ChannelWithStudio) o;

        return Objects.equals(videoStudio, that.videoStudio);
    }
}