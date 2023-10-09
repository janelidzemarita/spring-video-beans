package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.ChannelWithStudio;
import com.epam.rd.autotasks.confbeans.video.Video;
import com.epam.rd.autotasks.confbeans.video.VideoStudioImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.time.Period;


@Configuration
public class ChannelWithInjectedPrototypeVideoConfig {
    //Imagine a video channel releasing a new "Cat Failure Compilation" video each day.
    //
    //You must configure such a channel, but you must not use video studio beans.
    // Also, this channel bean must use a prototype scoped video bean to produce another video.

    VideoStudioImpl videoStudio = new VideoStudioImpl("Cat Failure Compilation", 7,
            LocalDateTime.of(2001, 10, 18, 10, 0), Period.ofDays(1));

    //configure a channel bean with a prototype scoped video bean
    @Bean
    @Scope("prototype")
    public Video video() {
        return channel().produce();
    }

    @Bean
    @Scope("singleton")
    public ChannelWithStudio channel() {
        return new ChannelWithStudio(videoStudio);
    }

}
