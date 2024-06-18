package com.github.yehortpk.analyzer.services;

import com.github.yehortpk.analyzer.models.DurationGroupingDTO;
import com.github.yehortpk.analyzer.repositories.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final TrackRepository trackRepository;

    public List<DurationGroupingDTO> getUserStatistics(int userId, int days) {
        ZonedDateTime startDate = ZonedDateTime.now().minusDays(days);

        List<DurationGroupingDTO> userTracks = trackRepository.findStatisticsByUserIdForDays(userId, startDate);

        return userTracks;
    }
}
