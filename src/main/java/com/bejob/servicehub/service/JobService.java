package com.bejob.servicehub.service;

import com.bejob.servicehub.dto.CreateJobRequest;
import com.bejob.servicehub.dto.JobResponse;
import com.bejob.servicehub.entity.Job;
import com.bejob.servicehub.entity.ServiceCategory;
import com.bejob.servicehub.entity.User;
import com.bejob.servicehub.enums.UserType;
import com.bejob.servicehub.exception.BusinessException;
import com.bejob.servicehub.repository.JobRepository;
import com.bejob.servicehub.repository.ServiceCategoryRepository;
import com.bejob.servicehub.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final ServiceCategoryRepository serviceCategoryRepository;

    public JobService(JobRepository jobRepository, UserRepository userRepository, ServiceCategoryRepository serviceCategoryRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.serviceCategoryRepository = serviceCategoryRepository;
    }

    public JobResponse create(CreateJobRequest request) {
        User client = userRepository.findById(request.id())
                .orElseThrow(() -> new BusinessException("Cliente não encontrado!"));

        if (client.getType() != UserType.CLIENT) {
            throw new BusinessException("Usuário informado não é do tipo CLIENT")
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
}
