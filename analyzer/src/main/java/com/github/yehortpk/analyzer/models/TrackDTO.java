package com.github.yehortpk.analyzer.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Setter
@Getter
@ToString
public class TrackDTO {
    @JsonProperty("user_id")
    private int userId;
    private String url;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private ZonedDateTime start;
    private ZonedDateTime finish = ZonedDateTime.now();

    public TrackDTO(int userId, String url, ZonedDateTime start) {
        this.userId = userId;
        this.url = url;
        this.start = start;
    }
}
