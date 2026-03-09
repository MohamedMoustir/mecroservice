package com.streaming.user.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class WatchStatsDTO {
    private Long userId;
    private long totalVideosWatched;
    private long completedVideos;
    private long totalTimeWatchedSeconds;
}