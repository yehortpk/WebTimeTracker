package com.github.yehortpk.analyzer.controllers;

import com.github.yehortpk.analyzer.models.Track;
import com.github.yehortpk.analyzer.models.TrackDTO;
import com.github.yehortpk.analyzer.repositories.TrackRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@Slf4j
class TrackerControllerITTest {
    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private TrackerController trackerController;

    private static MySQLContainer<?> mysql = new MySQLContainer<>(
            "mysql:8.0.37-debian"
    );

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        mysql.start();
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @BeforeEach
    void setUp() {
        trackRepository.deleteAll();
    }

    @Test
    void addTrackShouldCreateEntityInDB() {
        ZonedDateTime currentTime = ZonedDateTime.now();
        String someUrl = "some_url";
        TrackDTO track = new TrackDTO(1, someUrl, currentTime);
        trackerController.addTrack(track);
        Optional<Track> trackById = trackRepository.findById(1);
        assertThat(trackById.get().getUrl()).isEqualTo(someUrl);
    }
}