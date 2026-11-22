package com.vhp.budgeting.infrastructure.persistence.repository;

import com.vhp.budgeting.domain.Category;
import com.vhp.budgeting.domain.CategoryRepository;
import com.vhp.budgeting.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
public class JpaCategoryRepository implements CategoryRepository {

    private final CategoryEntityRepository repository;

    public JpaCategoryRepository(CategoryEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category save(Category category) {
        return repository.save(CategoryEntity.from(category))
                .toDomain();
    }

    @Override
    public List<Category> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(CategoryEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return repository.findByName(name)
                .map(CategoryEntity::toDomain);
    }
}
