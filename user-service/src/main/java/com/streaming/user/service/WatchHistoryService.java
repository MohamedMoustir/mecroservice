package com.streaming.user.service;
import com.streaming.user.dto.WatchHistoryRequestDTO;
import com.streaming.user.dto.WatchHistoryResponseDTO;
import com.streaming.user.dto.WatchStatsDTO;
import java.util.List;
public interface WatchHistoryService {
    List<WatchHistoryResponseDTO> getHistoryByUser(Long userId);
    WatchHistoryResponseDTO recordWatchHistory(WatchHistoryRequestDTO dto);
    WatchStatsDTO getWatchingStats(Long userId);
}