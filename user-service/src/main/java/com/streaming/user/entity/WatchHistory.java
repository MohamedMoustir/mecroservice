package com.streaming.user.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "watch_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WatchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long videoId;
    private LocalDateTime watchedAt;
    /** Progression en secondes */
    private Integer progressTime;
    private Boolean completed;
    @PrePersist
    public void prePersist() {
        if (this.watchedAt == null) this.watchedAt = LocalDateTime.now();
        if (this.completed == null) this.completed = false;
    }
}