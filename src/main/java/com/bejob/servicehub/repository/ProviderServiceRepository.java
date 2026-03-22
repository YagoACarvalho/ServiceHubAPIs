package com.bejob.servicehub.repository;

import com.bejob.servicehub.entity.ProviderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface ProviderServiceRepository extends JpaRepository<ProviderService, UUID> {

    boolean existsByProviderIdAndServiceCategoryId(UUID providerId, UUID serviceCategoryId);

    List<ProviderService> findByProviderId(UUID providerId);

}
