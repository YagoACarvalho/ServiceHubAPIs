package com.bejob.servicehub.repository;

import com.bejob.servicehub.entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, UUID> {

    Optional<ServiceCategory> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);

}
