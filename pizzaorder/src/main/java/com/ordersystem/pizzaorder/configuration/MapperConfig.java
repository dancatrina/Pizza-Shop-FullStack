package com.ordersystem.pizzaorder.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper configureMapper(){
        return new ModelMapper();
    }

}
