package com.bejob.servicehub.service;

import com.bejob.servicehub.dto.CreateProviderRequest;
import com.bejob.servicehub.dto.CreateUserRequest;
import com.bejob.servicehub.dto.ProviderResponse;
import com.bejob.servicehub.entity.Provider;
import com.bejob.servicehub.entity.User;
import com.bejob.servicehub.enums.UserType;
import com.bejob.servicehub.exception.BusinessException;
import com.bejob.servicehub.repository.ProviderRepository;
import com.bejob.servicehub.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;
    private final UserRepository userRepository;

    public ProviderService(ProviderRepository providerRepository, UserRepository userRepository) {
        this.providerRepository = providerRepository;
        this.userRepository = userRepository;
    }

    public ProviderResponse create(CreateProviderRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        if (user.getType() != UserType.PROVIDER) {
            throw new BusinessException("Usuário informado não é do tipo PROVIDER");
        }

        if (providerRepository.existsByUserId(user.getId())) {
            throw new BusinessException("Este usuário já possui perfil de prestador");
        }

        if (providerRepository.existsByDocument(request.document())) {
            throw new BusinessException("Documento já cadastrado");
        }

        Provider provider = Provider.builder()
                .user(user)
                .document(request.document())
                .city(request.City())
                .description(request.description())
                .build();

        Provider savedProvider = providerRepository.save(provider);

        return new ProviderResponse(
                savedProvider.getId(),
                savedProvider.getUser().getId(),
                savedProvider.getUser().getName(),
                savedProvider.getUser().getEmail(),
                savedProvider.getUser().getPhone(),
                savedProvider.getDocument(),
                savedProvider.getCity(),
                savedProvider.getDescription(),
                savedProvider.getVerified(),
                savedProvider.getAvailable(),
                savedProvider.getAvarageRating(),
                savedProvider.getCreatedAt()
        );
    }
}
