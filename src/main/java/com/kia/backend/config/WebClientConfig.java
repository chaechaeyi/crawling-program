package com.kia.backend.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                .responseTimeout(Duration.ofMillis(1000))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(1000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(1000, TimeUnit.MILLISECONDS)));

        WebClient client = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        return client;
    }

}
