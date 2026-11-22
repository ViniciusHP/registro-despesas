package com.vhp.budgeting.domain;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category save(Category transaction);
    List<Category> findAll();
    Optional<Category> findByName(String name);
}
