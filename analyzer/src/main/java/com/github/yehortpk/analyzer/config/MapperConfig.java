package com.github.yehortpk.analyzer.config;

import com.github.yehortpk.analyzer.models.Track;
import com.github.yehortpk.analyzer.models.TrackDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(TrackDTO.class, Track.class);

        return modelMapper;
    }
}
