package com.github.yehortpk.analyzer.repositories;

import com.github.yehortpk.analyzer.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Integer> {
}
