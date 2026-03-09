package com.streaming.user.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class WatchlistRequestDTO {
    @NotNull(message = "L identifiant utilisateur est obligatoire")
    private Long userId;
    @NotNull(message = "L identifiant video est obligatoire")
    private Long videoId;
}