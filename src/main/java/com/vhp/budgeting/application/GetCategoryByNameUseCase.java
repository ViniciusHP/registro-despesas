package com.vhp.budgeting.application;

import com.vhp.budgeting.application.output.CategoryOutput;
import com.vhp.budgeting.domain.CategoryRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class GetCategoryByNameUseCase {

    private final CategoryRepository categoryRepository;

    public GetCategoryByNameUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Tool(name = "get-category-by-name", description = "Obtém os dados da categoria financeira por meio do nome exato da categoria")
    public CategoryOutput execute(@ToolParam(description = "Nome exato da caregoria") String categoryName) {
        return categoryRepository.findByName(categoryName)
                .map(CategoryOutput::from)
                .orElseThrow(() -> new RuntimeException(String.format("A categoria '%s' não foi encontrada!", categoryName)));
    }
}
