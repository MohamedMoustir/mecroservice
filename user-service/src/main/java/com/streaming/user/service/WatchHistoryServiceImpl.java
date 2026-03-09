package com.streaming.user.service;
import com.streaming.user.dto.WatchHistoryRequestDTO;
import com.streaming.user.dto.WatchHistoryResponseDTO;
import com.streaming.user.dto.WatchStatsDTO;
import com.streaming.user.entity.WatchHistory;
import com.streaming.user.feign.VideoFeignClient;
import com.streaming.user.mapper.WatchHistoryMapper;
import com.streaming.user.repository.WatchHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class WatchHistoryServiceImpl implements WatchHistoryService {
    private final WatchHistoryRepository watchHistoryRepository;
    private final WatchHistoryMapper watchHistoryMapper;
    private final VideoFeignClient videoFeignClient;
    @Override
    @Transactional(readOnly = true)
    public List<WatchHistoryResponseDTO> getHistoryByUser(Long userId) {
        return watchHistoryRepository.findByUserIdOrderByWatchedAtDesc(userId).stream()
            .map(wh -> {
                WatchHistoryResponseDTO dto = watchHistoryMapper.toResponseDTO(wh);
                try { dto.setVideo(videoFeignClient.getVideoById(wh.getVideoId())); }
                catch (Exception e) { log.warn("Impossible de recuperer la video id={}", wh.getVideoId()); }
                return dto;
            }).toList();
    }
    @Override
    public WatchHistoryResponseDTO recordWatchHistory(WatchHistoryRequestDTO dto) {
        // Verifier que la video existe
        videoFeignClient.getVideoById(dto.getVideoId());
        WatchHistory saved = watchHistoryRepository.save(watchHistoryMapper.toEntity(dto));
        WatchHistoryResponseDTO response = watchHistoryMapper.toResponseDTO(saved);
        try { response.setVideo(videoFeignClient.getVideoById(saved.getVideoId())); }
        catch (Exception e) { log.warn("Impossible de recuperer les details de la video"); }
        return response;
    }
    @Override
    @Transactional(readOnly = true)
    public WatchStatsDTO getWatchingStats(Long userId) {
        long total = watchHistoryRepository.countByUserId(userId);
        long completed = watchHistoryRepository.countByUserIdAndCompleted(userId, true);
        long totalTime = watchHistoryRepository.sumProgressTimeByUserId(userId);
        return new WatchStatsDTO(userId, total, completed, totalTime);
    }
}