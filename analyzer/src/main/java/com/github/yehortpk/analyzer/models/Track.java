package com.github.yehortpk.analyzer.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String url;
    private ZonedDateTime start;
    private ZonedDateTime finish;

    public Track(int userId, String url, ZonedDateTime start) {
        this.userId = userId;
        this.url = url;
        this.start = start;
    }
}
