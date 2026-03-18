package com.bejob.servicehub.repository;

import com.bejob.servicehub.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProviderRepository extends JpaRepository<Provider, UUID> {

    Optional<Provider> findByUserId(UUID userId);

    boolean existsByUserId(UUID userId);

    boolean existsByDocument(String document);

}
