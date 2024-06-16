package com.github.yehortpk.analyzer.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class TrackDTO {
    private int userId;
    private String url;
    private LocalDateTime start;
    private LocalDateTime finish = LocalDateTime.now();

    public TrackDTO(int userId, String url, LocalDateTime start) {
        this.userId = userId;
        this.url = url;
        this.start = start;
    }
}
