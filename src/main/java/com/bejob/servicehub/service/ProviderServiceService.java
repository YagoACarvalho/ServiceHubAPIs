package com.bejob.servicehub.service;

import com.bejob.servicehub.dto.AssignServiceToProviderRequest;
import com.bejob.servicehub.dto.ProviderServiceResponse;
import com.bejob.servicehub.entity.Provider;
import com.bejob.servicehub.entity.ProviderService;
import com.bejob.servicehub.entity.ServiceCategory;
import com.bejob.servicehub.exception.BusinessException;
import com.bejob.servicehub.repository.ProviderRepository;
import com.bejob.servicehub.repository.ProviderServiceRepository;
import com.bejob.servicehub.repository.ServiceCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceService {

    // Este service faz cria o vínculo entre provider, category e serviço.

    private final ProviderRepository providerRepository;
    private final ServiceCategoryRepository serviceCategoryRepository;
    private final ProviderServiceRepository providerServiceRepository;

    public ProviderServiceService(ProviderRepository providerRepository, ServiceCategoryRepository serviceCategoryRepository, ProviderServiceRepository providerServiceRepository) {
        this.providerRepository = providerRepository;
        this.serviceCategoryRepository = serviceCategoryRepository;
        this.providerServiceRepository = providerServiceRepository;
    }

    public ProviderServiceResponse assign(AssignServiceToProviderRequest request) {
        Provider provider = providerRepository.findById(request.providerId())
                .orElseThrow(() -> new BusinessException("Prestador não encontrado"));

        ServiceCategory category = serviceCategoryRepository.findById(request.serviceCategoryId())
                .orElseThrow(() -> new BusinessException("Categoria de serviço não encontrada"));

        if (providerServiceRepository.existsByProviderIdAndServiceCategoryId(
                provider.getId(),
                category.getId()
        )) {
            throw new BusinessException("Esse serviço já foi vinculado ao prestador");
        }

        ProviderService providerService = ProviderService.builder()
                .provider(provider)
                .serviceCategory(category)
                .build();

        ProviderService saved = providerServiceRepository.save(providerService);

        return new ProviderServiceResponse(saved.getId(),
                saved.getProvider().getId(),
                saved.getProvider().getUser().getName(),
                saved.getServiceCategory().getId(),
                saved.getServiceCategory().getName(),
                saved.getCreatedAt());
    }
}
