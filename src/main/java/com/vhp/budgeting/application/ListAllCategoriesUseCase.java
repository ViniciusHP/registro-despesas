package com.vhp.budgeting.application;

import com.vhp.budgeting.application.output.CategoryOutput;
import com.vhp.budgeting.domain.CategoryRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllCategoriesUseCase {

    private final CategoryRepository categoryRepository;

    public ListAllCategoriesUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Tool(name = "list-all-categories", description = "Lista todas as categorias financeiras cadastradas")
    public List<CategoryOutput> execute() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryOutput::from)
                .toList();
    }
}
