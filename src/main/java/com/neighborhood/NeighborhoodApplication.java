package com.neighborhood;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class NeighborhoodApplication {

  public static void main(final String[] args) {

    SpringApplication.run(NeighborhoodApplication.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {

    return new ModelMapper();
  }

  @Configuration
  public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(final CorsRegistry registry) {

      registry.addMapping("/**")
          .allowedOrigins("http://localhost:3000")
          .allowedMethods("GET", "POST", "PUT", "DELETE")
          .allowedHeaders("*")
          .allowCredentials(true);
    }
  }
}
