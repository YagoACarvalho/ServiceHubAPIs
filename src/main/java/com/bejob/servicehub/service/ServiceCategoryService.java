package com.bejob.servicehub.service;

import com.bejob.servicehub.dto.CreateServiceCategoryRequest;
import com.bejob.servicehub.dto.ServiceCategoryResponse;
import com.bejob.servicehub.entity.ServiceCategory;
import com.bejob.servicehub.exception.BusinessException;
import com.bejob.servicehub.repository.ServiceCategoryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ServiceCategoryService {

    private final ServiceCategoryRepository repository;

    public ServiceCategoryService(ServiceCategoryRepository repository) {
        this.repository = repository;
    }

    public ServiceCategoryResponse create(CreateServiceCategoryRequest request)
    {
        if (repository.existsByNameIgnoreCase(request.name())) {
            throw new BusinessException("Categoria já existe");
        }

        ServiceCategory category = ServiceCategory.builder()
                .name(request.name().trim())
                .description(request.description())
                .build();

        ServiceCategory savedCategory = repository.save(category);

        return new ServiceCategoryResponse(savedCategory.getId(), savedCategory.getName(), savedCategory.getDescription(),savedCategory.getActive(), savedCategory.getCreatedAt());
    }
}
