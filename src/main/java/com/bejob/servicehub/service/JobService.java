package com.bejob.servicehub.service;

import com.bejob.servicehub.dto.AcceptJobRequest;
import com.bejob.servicehub.dto.CreateJobRequest;
import com.bejob.servicehub.dto.JobResponse;
import com.bejob.servicehub.dto.JobSearchResponse;
import com.bejob.servicehub.entity.Job;
import com.bejob.servicehub.entity.Provider;
import com.bejob.servicehub.entity.ServiceCategory;
import com.bejob.servicehub.entity.User;
import com.bejob.servicehub.enums.JobStatus;
import com.bejob.servicehub.enums.UserType;
import com.bejob.servicehub.exception.BusinessException;
import com.bejob.servicehub.repository.*;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final ServiceCategoryRepository serviceCategoryRepository;
    private final ProviderRepository providerRepository;
    private final ProviderServiceRepository providerServiceRepository;

    public JobService(JobRepository jobRepository, UserRepository userRepository, ServiceCategoryRepository serviceCategoryRepository, ProviderRepository providerRepository, ProviderServiceRepository providerServiceRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.serviceCategoryRepository = serviceCategoryRepository;
        this.providerRepository = providerRepository;
        this.providerServiceRepository = providerServiceRepository;
    }

    public JobResponse create(CreateJobRequest request) {
        User client = userRepository.findById(request.id())
                .orElseThrow(() -> new BusinessException("Cliente não encontrado!"));

        if (client.getType() != UserType.CLIENT) {
            throw new BusinessException("Usuário informado não é do tipo CLIENT");
        }

        ServiceCategory category = serviceCategoryRepository.findById(request.serviceCategoryId())
                .orElseThrow(() -> new BusinessException("Categoria de serviço não encontrada"));

        if (!Boolean.TRUE.equals(category.getActive())) {
            throw new BusinessException("Categoria de serviço está inativa");
        }

        Job job = Job.builder()
                .client(client)
                .serviceCategory(category)
                .description(request.description())
                .city(request.city())
                .address(request.address())
                .build();

        Job savedJob = jobRepository.save(job);

        return new JobResponse(
                job.getId(),
                job.getClient().getId(),
                job.getClient().getName(),
                job.getServiceCategory().getId(),
                job.getServiceCategory().getName(),
                job.getDescription(),
                job.getCity(),
                job.getAddress(),
                job.getStatus(),
                job.getProvider().getId(),
                job.getProvider().getUser().getName(),
                job.getCreatedAt());
    }


    public JobResponse accept(AcceptJobRequest request) {
        Job job = jobRepository.findById(request.jobId())
                .orElseThrow(() -> new BusinessException("Job não encontrado"));

        if (job.getStatus() != JobStatus.OPEN) {
            throw new BusinessException("Esse job mão está mais disponível para aceite");
        }

        Provider provider = providerRepository.findById(request.providerId())
                .orElseThrow(() -> new BusinessException("Prestador não encontrado"));

        if (!Boolean.TRUE.equals(provider.getAvailable())) {
            throw new BusinessException("Prestador indisponível");
        }

        if (!provider.getCity().equalsIgnoreCase(job.getCity())) {
            throw new BusinessException("Prestador não atende a cidade do job");
        }

        boolean providerHasCategory = providerServiceRepository.existsByProviderIdAndServiceCategoryId(
                provider.getId(),
                job.getServiceCategory().getId()
        );

        if (!providerHasCategory) {
            throw new BusinessException("Prestador não atende essa categoria de serviço");
        }

        job.setProvider(provider);
        job.setStatus(JobStatus.ACCEPTED);

        Job savedJob = jobRepository.save(job);

        return toResponse(savedJob);
    }

    private JobResponse toResponse(Job job) {
        return new JobResponse(
                job.getId(),
                job.getClient().getId(),
                job.getClient().getName(),
                job.getServiceCategory().getId(),
                job.getServiceCategory().getName(),
                job.getDescription(),
                job.getCity(),
                job.getAddress(),
                job.getStatus(),
                job.getProvider() != null ? job.getProvider().getId() : null,
                job.getProvider() != null ? job.getProvider().getUser().getName() : null,
                job.getCreatedAt()
            );
        }

    public List<JobSearchResponse> findOpenJobs(String city, UUID categoryId) {

        List<Job> jobs;

        if (categoryId != null) {
            jobs = jobRepository.findByStatusAndCityIgnoreCaseAndServiceCategoryId(
                    JobStatus.OPEN,
                    city,
                    categoryId
            );
        } else {
            jobs = jobRepository.findByStatusAndCityIgnoreCase(
                    JobStatus.OPEN,
                    city
            );
        }

        return jobs.stream()
                .map(job -> new JobSearchResponse(
                        job.getId(),
                        job.getDescription(),
                        job.getCity(),
                        job.getAddress(),
                        job.getStatus(),
                        job.getServiceCategory().getId(),
                        job.getServiceCategory().getName(),
                        job.getClient().getId(),
                        job.getClient().getName(),
                        job.getCreatedAt()
                ))
                .toList();
    }

}
