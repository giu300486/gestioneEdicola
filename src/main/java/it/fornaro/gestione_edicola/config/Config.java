package it.fornaro.gestione_edicola.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:application.properties")
public class Config {

    @Value("${clientId}") private String cliendId;
    @Value("${dataLoginUri}") private String dataLoginUri;

    public Config() {}

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    public String getCliendId() {
        return cliendId;
    }

    public String getDataLoginUri() {
        return dataLoginUri;
    }
}
