package com.vhp.budgeting.infrastructure.persistence.repository;

import com.vhp.budgeting.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryEntityRepository extends CrudRepository<CategoryEntity, UUID> {
    Optional<CategoryEntity> findByName(String name);
}
