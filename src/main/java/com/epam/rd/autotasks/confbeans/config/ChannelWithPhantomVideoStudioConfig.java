package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import com.epam.rd.autotasks.confbeans.video.VideoStudioImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.time.Period;

@Configuration
public class ChannelWithPhantomVideoStudioConfig {
    //  We are configuring the "Cat & Curios" franchise just as in ChannelWithVideoStudioConfig.
    //
    //But you must not use a bean of video studio. Use a prototype scoped bean of video instead.

    VideoStudioImpl videoStudio = new VideoStudioImpl("Cat & Curious", 8,
            LocalDateTime.of(2001, 10, 18, 10, 0), Period.ofYears(2));

    @Bean
    @Scope("prototype")
    public Video video() {
        return videoStudio.produce();
    }

    @Bean
    public Channel channel() {
        Channel channel = new Channel();
        while (videoStudio.getLastPart() <= videoStudio.getFranchiseSize()) {
            channel.addVideo(videoStudio.produce());
        }
        return channel;
    }
}
