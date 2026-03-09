package com.streaming.user.feign;
import com.streaming.user.dto.VideoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
/**
 * Client Feign pour communiquer avec le video-service via Eureka.
 * Utilise le load balancing automatique (lb://video-service).
 */
@FeignClient(name = "video-service", path = "/api/videos")
public interface VideoFeignClient {
    @GetMapping("/{id}")
    VideoResponseDTO getVideoById(@PathVariable("id") Long id);
    @GetMapping
    List<VideoResponseDTO> getAllVideos();
}