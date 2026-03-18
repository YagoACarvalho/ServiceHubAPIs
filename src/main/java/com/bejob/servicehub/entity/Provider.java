package com.bejob.servicehub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "providers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false, unique = true, length = 20)
    private String document;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean verified;

    @Column(nullable = false)
    private Boolean available;

    @Column(nullable = false)
    private Double avarageRating;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();

        if (this.verified == null) {
            this.verified = false;
        }

        if (this.available == null) {
            this.available = true;
        }

        if (this.avarageRating == null) {
            this.avarageRating = 0.0;
        }
    }

}
