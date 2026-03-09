package com.streaming.user.controller;
import com.streaming.user.dto.WatchHistoryRequestDTO;
import com.streaming.user.dto.WatchHistoryResponseDTO;
import com.streaming.user.dto.WatchStatsDTO;
import com.streaming.user.service.WatchHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class WatchHistoryController {
    private final WatchHistoryService watchHistoryService;
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WatchHistoryResponseDTO>> getHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(watchHistoryService.getHistoryByUser(userId));
    }
    @PostMapping
    public ResponseEntity<WatchHistoryResponseDTO> recordHistory(@Valid @RequestBody WatchHistoryRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(watchHistoryService.recordWatchHistory(dto));
    }
    @GetMapping("/user/{userId}/stats")
    public ResponseEntity<WatchStatsDTO> getStats(@PathVariable Long userId) {
        return ResponseEntity.ok(watchHistoryService.getWatchingStats(userId));
    }
}