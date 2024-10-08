package br.com.acbueno.footaball.olap.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppRestTemplateConfig {

  @Bean
  public RestTemplate builder(RestTemplateBuilder restTemplate) {
    return restTemplate.build();
  }

}
