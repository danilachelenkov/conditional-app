package ru.netology.conditionalapp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.conditionalapp.profile.DevProfile;
import ru.netology.conditionalapp.profile.ProductionProfile;
import ru.netology.conditionalapp.profile.SystemProfile;


@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(
            value = "netology.profile.dev",
            havingValue = "true",
            matchIfMissing = true
    )
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(
            value = "netology.profile.dev",
            havingValue = "false"
    )
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
