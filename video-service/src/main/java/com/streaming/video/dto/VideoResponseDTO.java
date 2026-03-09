package com.streaming.video.dto;
import com.streaming.video.entity.VideoCategory;
import com.streaming.video.entity.VideoType;
import lombok.Data;
@Data
public class VideoResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String thumbnailUrl;
    private String trailerUrl;
    private Integer duration;
    private Integer releaseYear;
    private VideoType type;
    private VideoCategory category;
    private Double rating;
    private String director;
    private String cast;
}