package com.bejob.servicehub.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "provider_services",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"provider_id"} )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderService {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_category_id", nullable = false)
    private ServiceCategory serviceCategory;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
