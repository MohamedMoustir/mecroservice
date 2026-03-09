package com.streaming.user.service;
import com.streaming.user.dto.WatchlistRequestDTO;
import com.streaming.user.dto.WatchlistResponseDTO;
import com.streaming.user.entity.Watchlist;
import com.streaming.user.exception.ResourceNotFoundException;
import com.streaming.user.feign.VideoFeignClient;
import com.streaming.user.mapper.WatchlistMapper;
import com.streaming.user.repository.WatchlistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class WatchlistServiceImpl implements WatchlistService {
    private final WatchlistRepository watchlistRepository;
    private final WatchlistMapper watchlistMapper;
    private final VideoFeignClient videoFeignClient;
    @Override
    @Transactional(readOnly = true)
    public List<WatchlistResponseDTO> getWatchlistByUser(Long userId) {
        return watchlistRepository.findByUserId(userId).stream()
            .map(w -> {
                WatchlistResponseDTO dto = watchlistMapper.toResponseDTO(w);
                try { dto.setVideo(videoFeignClient.getVideoById(w.getVideoId())); }
                catch (Exception e) { log.warn("Impossible de recuperer la video id={}", w.getVideoId()); }
                return dto;
            }).toList();
    }
    @Override
    public WatchlistResponseDTO addToWatchlist(WatchlistRequestDTO dto) {
        if (watchlistRepository.existsByUserIdAndVideoId(dto.getUserId(), dto.getVideoId()))
            throw new IllegalStateException("Video deja dans la watchlist");
        // Verifier que la video existe via Feign
        videoFeignClient.getVideoById(dto.getVideoId());
        Watchlist saved = watchlistRepository.save(watchlistMapper.toEntity(dto));
        WatchlistResponseDTO response = watchlistMapper.toResponseDTO(saved);
        try { response.setVideo(videoFeignClient.getVideoById(saved.getVideoId())); }
        catch (Exception e) { log.warn("Impossible de recuperer les details de la video"); }
        return response;
    }
    @Override
    public void removeFromWatchlist(Long watchlistId) {
        if (!watchlistRepository.existsById(watchlistId))
            throw new ResourceNotFoundException("Entree watchlist non trouvee: id=" + watchlistId);
        watchlistRepository.deleteById(watchlistId);
    }
    @Override
    public void removeFromWatchlistByUserAndVideo(Long userId, Long videoId) {
        if (!watchlistRepository.existsByUserIdAndVideoId(userId, videoId))
            throw new ResourceNotFoundException("Video non trouvee dans la watchlist");
        watchlistRepository.deleteByUserIdAndVideoId(userId, videoId);
    }
}