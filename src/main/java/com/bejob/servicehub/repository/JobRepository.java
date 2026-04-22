package com.bejob.servicehub.repository;

import com.bejob.servicehub.entity.Job;
import java.util.List;

import com.bejob.servicehub.enums.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {

    List<Job> findByClientId(UUID clientId);

    List<Job> findByProviderId(UUID providerId);

    List<Job> findByStatus(JobStatus status);

    List<Job> findByStatusAndCityIgnoreCaseAndServiceCategoryId(
            JobStatus status,
            String city,
            UUID serviceCategoryId
    );

    List<Job> findByStatusAndCityIgnoreCase(JobStatus jobStatus, String city);

    List<Job> findByProviderIdOrderByCreatedAtDesc(UUID providerId);
}
