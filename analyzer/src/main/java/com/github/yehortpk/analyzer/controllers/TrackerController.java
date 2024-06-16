package com.github.yehortpk.analyzer.controllers;

import com.github.yehortpk.analyzer.models.Track;
import com.github.yehortpk.analyzer.models.TrackDTO;
import com.github.yehortpk.analyzer.services.TrackerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/track")
@RequiredArgsConstructor
@Slf4j
public class TrackerController {
    private final TrackerService trackerService;
    private final ModelMapper modelMapper;

    @PostMapping
    public void addTrack(TrackDTO trackDTO) {
        Track track = modelMapper.map(trackDTO, Track.class);
        trackerService.saveTrack(track);
    }
}
