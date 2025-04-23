package com.zero.downtime.userservice.config;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.util.UnleashConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnleashClientConfig {

    @Bean
    public Unleash unleash() {
        UnleashConfig config = UnleashConfig.builder()
                .appName("unleash-onboarding-java")
                .instanceId("unleash-onboarding-instance")
                .unleashAPI("https://eu.app.unleash-hosted.com/demo/api/") // Replace with your URL
                .apiKey("userservice:development.25ac42f7bbd06fc46b1447124b7261ba2df8095e67726b4ea268d624") // Replace with the actual API key
                .build();

        return new DefaultUnleash(config);
    }
}
