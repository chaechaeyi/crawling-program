package com.kia.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * async configuration
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "crawlingTaskExecutor", destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor crawlingTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1); // 최소 1개 thread는 보유하고 working이 됨 (무조건 점유하고 있는 thread 값)
        executor.setMaxPoolSize(3); // queue가 full일 경우 max pool size만큼 thread 생성
        executor.setQueueCapacity(2); // queue에는 2개까지의 request를 담도록 한다
        executor.setKeepAliveSeconds(5); // 5초 동안 working하지 않으면 자원 반납
        return executor;
    }
}
