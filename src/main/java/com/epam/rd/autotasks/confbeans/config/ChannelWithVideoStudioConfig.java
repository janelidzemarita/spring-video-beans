package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.VideoStudio;
import com.epam.rd.autotasks.confbeans.video.VideoStudioImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.Period;

@Configuration
public class ChannelWithVideoStudioConfig {
    //Define a video studio bean, creating movies of "Cat & Curios" franchise.
    //
    //First movie has a name "Cat & Curios 1" and was released at October, 18 2001, 10:00.
    //
    //All sequels has incremented number in their name and releases exactly two years after previous release.
    //
    //Define a channel bean containing eight movies of the franchise.
    //
    //Be sure to avoid video beans.

    @Bean
    public VideoStudio videoStudio() {
        return new VideoStudioImpl("Cat & Curious", 8,
                LocalDateTime.of(2001, 10, 18, 10, 0), Period.ofYears(2));
    }

    @Bean
    public Channel channel(VideoStudio studio) {
        VideoStudioImpl videoStudio = (VideoStudioImpl) studio;
        Channel channel = new Channel();
        while (videoStudio.getLastPart() <= videoStudio.getFranchiseSize()) {
            channel.addVideo(videoStudio.produce());
        }
        return channel;
    }



}
