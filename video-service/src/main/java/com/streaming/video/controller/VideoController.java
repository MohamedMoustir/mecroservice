package com.streaming.video.controller;

import com.streaming.video.dto.VideoRequestDTO;
import com.streaming.video.dto.VideoResponseDTO;
import com.streaming.video.entity.VideoCategory;
import com.streaming.video.entity.VideoType;
import com.streaming.video.service.VideoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class VideoController {

    private final VideoService videoService;

    @GetMapping
    public ResponseEntity<List<VideoResponseDTO>> getAllVideos() {
        return ResponseEntity.ok(videoService.getAllVideos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoResponseDTO> getVideoById(@PathVariable Long id) {
        return ResponseEntity.ok(videoService.getVideoById(id));
    }

    @PostMapping
    public ResponseEntity<VideoResponseDTO> createVideo(@RequestBody @Valid VideoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(videoService.createVideo(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoResponseDTO> updateVideo(
            @PathVariable Long id,
            @RequestBody @Valid VideoRequestDTO dto) {
        return ResponseEntity.ok(videoService.updateVideo(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<VideoResponseDTO>> getByType(@PathVariable VideoType type) {
        return ResponseEntity.ok(videoService.getVideosByType(type));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<VideoResponseDTO>> getByCategory(@PathVariable VideoCategory category) {
        return ResponseEntity.ok(videoService.getVideosByCategory(category));
    }

    @GetMapping("/search")
    public ResponseEntity<List<VideoResponseDTO>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(videoService.searchVideosByTitle(title));
    }

    @GetMapping("/rating")
    public ResponseEntity<List<VideoResponseDTO>> getByMinRating(@RequestParam Double min) {
        return ResponseEntity.ok(videoService.getVideosByMinRating(min));
    }
}

