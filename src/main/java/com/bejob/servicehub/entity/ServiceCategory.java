package com.bejob.servicehub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "service_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 300)
    private String description;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();

        if (this.active == null) {
            this.active = true;
        }
    }
}
