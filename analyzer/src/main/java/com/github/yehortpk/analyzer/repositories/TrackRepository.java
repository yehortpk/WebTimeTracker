package com.github.yehortpk.analyzer.repositories;

import com.github.yehortpk.analyzer.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.github.yehortpk.analyzer.models.DurationGroupingDTO;

import java.time.ZonedDateTime;
import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Integer> {
    @Query("SELECT new com.github.yehortpk.analyzer.models.DurationGroupingDTO(t.userId, t.url, " +
            "SUM(TIMESTAMPDIFF(SECOND, t.start, t.finish)) AS durationInSeconds ) " +
            "FROM Track t " +
            "WHERE t.start >= :startDate AND t.userId = :userId " +
            "GROUP BY t.userId, t.url ORDER BY t.userId ASC, durationInSeconds DESC")
    List<DurationGroupingDTO> findStatisticsByUserIdForDays(int userId, ZonedDateTime startDate);
}
