package com.streaming.user.service;
import com.streaming.user.dto.WatchlistRequestDTO;
import com.streaming.user.dto.WatchlistResponseDTO;
import java.util.List;
public interface WatchlistService {
    List<WatchlistResponseDTO> getWatchlistByUser(Long userId);
    WatchlistResponseDTO addToWatchlist(WatchlistRequestDTO dto);
    void removeFromWatchlist(Long watchlistId);
    void removeFromWatchlistByUserAndVideo(Long userId, Long videoId);
}