package com.streaming.video.repository;
import com.streaming.video.entity.Video;
import com.streaming.video.entity.VideoCategory;
import com.streaming.video.entity.VideoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByType(VideoType type);
    List<Video> findByCategory(VideoCategory category);
    List<Video> findByTitleContainingIgnoreCase(String title);
    List<Video> findByReleaseYear(Integer year);
    List<Video> findByRatingGreaterThanEqual(Double minRating);
}