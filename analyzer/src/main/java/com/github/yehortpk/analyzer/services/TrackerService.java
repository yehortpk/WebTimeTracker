package com.github.yehortpk.analyzer.services;

import com.github.yehortpk.analyzer.models.Track;
import com.github.yehortpk.analyzer.repositories.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackerService {
    private final TrackRepository trackRepository;

    public void saveTrack(Track track){
        trackRepository.save(track);
    }
}
