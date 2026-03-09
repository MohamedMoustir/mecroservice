package com.streaming.user.dto;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class WatchlistResponseDTO {
    private Long id;
    private Long userId;
    private Long videoId;
    private LocalDateTime addedAt;
    private VideoResponseDTO video;
}