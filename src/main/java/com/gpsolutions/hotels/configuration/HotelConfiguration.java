package com.gpsolutions.hotels.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HotelConfiguration implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new GroupingFieldConverter());
    }

    @Bean
    public OpenAPI titleOpenAPI() {
        return new OpenAPI().info(new Info().title("Hotel API").version("0.0.1"));
    }

}
