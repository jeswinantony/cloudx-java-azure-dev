package com.chtrembl.petstore.order.config;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCosmosRepositories(basePackages = "com.chtrembl.petstore.order.service")
public class CosmosConfiguration extends AbstractCosmosConfiguration {

    @Override
    protected String getDatabaseName() {
        return "atpetstore";
    }

    @Bean
    public CosmosClientBuilder cosmosClientBuilder(@Value("${azure.cosmos.key}") String key) {
        return new CosmosClientBuilder()
                .endpoint("https://atpetstore.documents.azure.com:443/")
                .key(key);
    }
}
