package com.streaming.video.service;
import com.streaming.video.dto.VideoRequestDTO;
import com.streaming.video.dto.VideoResponseDTO;
import com.streaming.video.entity.VideoCategory;
import com.streaming.video.entity.VideoType;
import java.util.List;
public interface VideoService {
    List<VideoResponseDTO> getAllVideos();
    VideoResponseDTO getVideoById(Long id);
    VideoResponseDTO createVideo(VideoRequestDTO requestDTO);
    VideoResponseDTO updateVideo(Long id, VideoRequestDTO requestDTO);
    void deleteVideo(Long id);
    List<VideoResponseDTO> getVideosByType(VideoType type);
    List<VideoResponseDTO> getVideosByCategory(VideoCategory category);
    List<VideoResponseDTO> searchVideosByTitle(String title);
    List<VideoResponseDTO> getVideosByMinRating(Double minRating);
}