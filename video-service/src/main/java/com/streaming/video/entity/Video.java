package com.streaming.video.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(length = 2000)
    private String description;
    private String thumbnailUrl;
    /** URL embed YouTube uniquement (ex: https://www.youtube.com/embed/VIDEO_ID) */
    private String trailerUrl;
    /** Duree en minutes */
    private Integer duration;
    private Integer releaseYear;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VideoType type;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VideoCategory category;
    /** Note sur 10 */
    private Double rating;
    private String director;
    @Column(name = "cast_members", length = 1000)
    private String cast;
}