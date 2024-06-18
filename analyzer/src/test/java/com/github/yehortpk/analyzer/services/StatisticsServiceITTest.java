package com.github.yehortpk.analyzer.services;

import com.github.yehortpk.analyzer.models.DurationGroupingDTO;
import com.github.yehortpk.analyzer.models.Track;
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

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Testcontainers
@Slf4j
class StatisticsServiceITTest {
    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private StatisticsService statisticsService;

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
    void getUserStatisticsShouldReturnRightCumulativeStatistics() {
        trackRepository.save(new Track(1, "some_url", ZonedDateTime.now().minusSeconds(10), ZonedDateTime.now()));
        trackRepository.save(new Track(1, "some_url", ZonedDateTime.now().minusSeconds(5), ZonedDateTime.now()));

        List<DurationGroupingDTO> userStatistics = statisticsService.getUserStatistics(1, 1);
        assertThat(userStatistics.getFirst().getDuration()).isEqualTo(Duration.ofSeconds(15));
    }

    @Test
    void getUserStatisticsShouldReturnRightCumulativeStatisticsWhenOneRecordLater() {
        trackRepository.save(new Track(1, "some_url", ZonedDateTime.now().minusSeconds(15), ZonedDateTime.now()));
        trackRepository.save(new Track(1, "some_url", ZonedDateTime.now().minusSeconds(10), ZonedDateTime.now()));
        trackRepository.save(new Track(1, "some_url", ZonedDateTime.now().minusDays(2), ZonedDateTime.now()));

        List<DurationGroupingDTO> userStatistics = statisticsService.getUserStatistics(1, 1);
        assertThat(userStatistics.getFirst().getDuration()).isEqualTo(Duration.ofSeconds(25));
    }

    @Test
    void getUserStatisticsShouldReturnRightCumulativeStatisticsWhenAllRecordsLater() {
        trackRepository.save(new Track(1, "some_url", ZonedDateTime.now().minusSeconds(15), ZonedDateTime.now()));
        trackRepository.save(new Track(1, "some_url", ZonedDateTime.now().minusSeconds(10), ZonedDateTime.now()));
        trackRepository.save(new Track(1, "some_url", ZonedDateTime.now().minusDays(2), ZonedDateTime.now()));

        List<DurationGroupingDTO> userStatistics = statisticsService.getUserStatistics(1, 0);
        assertThat(userStatistics.size()).isEqualTo(0);
    }
}