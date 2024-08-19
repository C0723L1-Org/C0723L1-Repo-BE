
package org.c07.movie_booking.configuration;

import org.c07.movie_booking.dto.MovieDTO;
import org.c07.movie_booking.model.Movie;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}


