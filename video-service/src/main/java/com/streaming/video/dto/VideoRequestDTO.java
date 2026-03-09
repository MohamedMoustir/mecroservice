package com.streaming.video.dto;
import com.streaming.video.entity.VideoCategory;
import com.streaming.video.entity.VideoType;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data
public class VideoRequestDTO {
    @NotBlank(message = "Le titre est obligatoire")
    private String title;
    @Size(max = 2000)
    private String description;
    private String thumbnailUrl;
    @Pattern(regexp = "^https://www\\.youtube\\.com/embed/.*",
             message = "L URL doit etre une URL embed YouTube: https://www.youtube.com/embed/...")
    private String trailerUrl;
    @Positive(message = "La duree doit etre positive (en minutes)")
    private Integer duration;
    @Min(1888) @Max(2100)
    private Integer releaseYear;
    @NotNull(message = "Le type est obligatoire (FILM ou SERIE)")
    private VideoType type;
    @NotNull(message = "La categorie est obligatoire")
    private VideoCategory category;
    @DecimalMin("0.0") @DecimalMax("10.0")
    private Double rating;
    private String director;
    private String cast;
}