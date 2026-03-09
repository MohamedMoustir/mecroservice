package com.streaming.user.dto;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data
public class WatchHistoryRequestDTO {
    @NotNull private Long userId;
    @NotNull private Long videoId;
    @Min(0)
    private Integer progressTime;
    private Boolean completed;
}