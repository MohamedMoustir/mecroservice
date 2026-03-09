package com.streaming.user.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "watchlist", uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "videoId"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long videoId;
    private LocalDateTime addedAt;
    @PrePersist
    public void prePersist() {
        this.addedAt = LocalDateTime.now();
    }
}