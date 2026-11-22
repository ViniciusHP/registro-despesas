package com.vhp.budgeting.application;

import com.vhp.budgeting.application.input.PersistCategoryInput;
import com.vhp.budgeting.application.output.CategoryOutput;
import com.vhp.budgeting.domain.Category;
import com.vhp.budgeting.domain.CategoryRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class PersistCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public PersistCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Tool(name = "persist-category", description = "Persiste uma nova categoria financeira")
    public CategoryOutput execute(PersistCategoryInput input) {
        var category = categoryRepository.save(new Category(input.name()));
        return CategoryOutput.from(category);
    }
}
