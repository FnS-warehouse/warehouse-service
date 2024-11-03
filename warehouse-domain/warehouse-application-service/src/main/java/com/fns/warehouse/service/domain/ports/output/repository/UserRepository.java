package com.fns.warehouse.service.domain.ports.output.repository;

import com.fns.warehouse.service.domain.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findById(UUID userId);
}
