package com.streaming.user.dto;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class WatchHistoryResponseDTO {
    private Long id;
    private Long userId;
    private Long videoId;
    private LocalDateTime watchedAt;
    private Integer progressTime;
    private Boolean completed;
    private VideoResponseDTO video;
}