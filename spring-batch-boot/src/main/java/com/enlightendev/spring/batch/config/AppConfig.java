package com.enlightendev.spring.batch.config;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
@EnableBatchProcessing(modular=true)
public class AppConfig extends DefaultBatchConfigurer {

    @Bean
    public ApplicationContextFactory csvJob() {
        return new GenericApplicationContextFactory(CSVTransformBatchConfiguration.class);
    }

    @Bean
    public ApplicationContextFactory stockJob() {
        return new GenericApplicationContextFactory(ImportStocksBatchJobConfiguration.class);
    }
}
