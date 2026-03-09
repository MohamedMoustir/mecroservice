package com.streaming.user.repository;
import com.streaming.user.entity.WatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface WatchHistoryRepository extends JpaRepository<WatchHistory, Long> {
    List<WatchHistory> findByUserIdOrderByWatchedAtDesc(Long userId);
    long countByUserId(Long userId);
    long countByUserIdAndCompleted(Long userId, Boolean completed);
    @Query("SELECT COALESCE(SUM(wh.progressTime), 0) FROM WatchHistory wh WHERE wh.userId = :userId")
    long sumProgressTimeByUserId(Long userId);
}