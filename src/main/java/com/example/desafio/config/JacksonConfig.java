package com.example.desafio.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Classe de configuração do Jackson
 */
@Configuration
public class JacksonConfig {

    /**
     * Método que configura o Jackson
     *
     * @return Jackson2ObjectMapperBuilder
     */
    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
        builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
        return builder;
    }
}