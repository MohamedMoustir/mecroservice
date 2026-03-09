package com.streaming.video.service;
import com.streaming.video.dto.VideoRequestDTO;
import com.streaming.video.dto.VideoResponseDTO;
import com.streaming.video.entity.Video;
import com.streaming.video.entity.VideoCategory;
import com.streaming.video.entity.VideoType;
import com.streaming.video.exception.VideoNotFoundException;
import com.streaming.video.mapper.VideoMapper;
import com.streaming.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;
    @Override
    @Transactional(readOnly = true)
    public List<VideoResponseDTO> getAllVideos() {
        log.info("Recuperation de toutes les videos");
        return videoRepository.findAll().stream().map(videoMapper::toResponseDTO).toList();
    }
    @Override
    @Transactional(readOnly = true)
    public VideoResponseDTO getVideoById(Long id) {
        log.info("Recuperation video id={}", id);
        return videoMapper.toResponseDTO(
            videoRepository.findById(id).orElseThrow(() -> new VideoNotFoundException("Video non trouvee: id=" + id))
        );
    }
    @Override
    public VideoResponseDTO createVideo(VideoRequestDTO requestDTO) {
        log.info("Creation video: {}", requestDTO.getTitle());
        return videoMapper.toResponseDTO(videoRepository.save(videoMapper.toEntity(requestDTO)));
    }
    @Override
    public VideoResponseDTO updateVideo(Long id, VideoRequestDTO requestDTO) {
        log.info("Mise a jour video id={}", id);
        Video video = videoRepository.findById(id)
            .orElseThrow(() -> new VideoNotFoundException("Video non trouvee: id=" + id));
        videoMapper.updateEntityFromDTO(requestDTO, video);
        return videoMapper.toResponseDTO(videoRepository.save(video));
    }
    @Override
    public void deleteVideo(Long id) {
        log.info("Suppression video id={}", id);
        if (!videoRepository.existsById(id)) throw new VideoNotFoundException("Video non trouvee: id=" + id);
        videoRepository.deleteById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public List<VideoResponseDTO> getVideosByType(VideoType type) {
        return videoRepository.findByType(type).stream().map(videoMapper::toResponseDTO).toList();
    }
    @Override
    @Transactional(readOnly = true)
    public List<VideoResponseDTO> getVideosByCategory(VideoCategory category) {
        return videoRepository.findByCategory(category).stream().map(videoMapper::toResponseDTO).toList();
    }
    @Override
    @Transactional(readOnly = true)
    public List<VideoResponseDTO> searchVideosByTitle(String title) {
        return videoRepository.findByTitleContainingIgnoreCase(title).stream().map(videoMapper::toResponseDTO).toList();
    }
    @Override
    @Transactional(readOnly = true)
    public List<VideoResponseDTO> getVideosByMinRating(Double minRating) {
        return videoRepository.findByRatingGreaterThanEqual(minRating).stream().map(videoMapper::toResponseDTO).toList();
    }
}