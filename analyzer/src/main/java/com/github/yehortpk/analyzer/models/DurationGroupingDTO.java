package com.github.yehortpk.analyzer.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DurationGroupingDTO {
    private int userId;
    private String url;
    private Duration duration;

    public DurationGroupingDTO(int userId, String url, double durationSec) {
        this.userId = userId;
        this.url = url;
        this.duration = Duration.ofSeconds((long) durationSec);
    }
}
