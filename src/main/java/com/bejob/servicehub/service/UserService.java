package com.bejob.servicehub.service;

import com.bejob.servicehub.dto.CreateUserRequest;
import com.bejob.servicehub.dto.UserResponse;
import com.bejob.servicehub.entity.User;
import com.bejob.servicehub.exception.BusinessException;
import com.bejob.servicehub.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse create(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new BusinessException("E-mail já cadastrado");
        }

        if (userRepository.existsByPhone(request.phone())) {
            throw new BusinessException("telefone já cadastrado");
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .phone(request.phone())
                .type(request.type())
                .build();

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getType(),
                savedUser.getActive(),
                savedUser.getCreatedAt()
        );
    }
}
