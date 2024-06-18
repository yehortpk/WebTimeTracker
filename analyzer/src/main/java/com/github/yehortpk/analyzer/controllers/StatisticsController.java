package com.github.yehortpk.analyzer.controllers;

import com.github.yehortpk.analyzer.models.DurationGroupingDTO;
import com.github.yehortpk.analyzer.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping
    public List<DurationGroupingDTO> getStatisticsForDays(@RequestParam int days, @RequestHeader("user_id") int userId) {
        return statisticsService.getUserStatistics(userId, days);
    }
    
}
