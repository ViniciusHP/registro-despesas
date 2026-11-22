package com.vhp.budgeting.infrastructure.http;

import com.vhp.budgeting.application.ListAllCategoriesUseCase;
import com.vhp.budgeting.application.PersistCategoryUseCase;
import com.vhp.budgeting.application.input.PersistCategoryInput;
import com.vhp.budgeting.application.output.CategoryOutput;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final PersistCategoryUseCase persistCategoryUseCase;
    private final ListAllCategoriesUseCase listAllCategoriesUseCase;

    public CategoryController(PersistCategoryUseCase persistCategoryUseCase, ListAllCategoriesUseCase listAllCategoriesUseCase) {
        this.persistCategoryUseCase = persistCategoryUseCase;
        this.listAllCategoriesUseCase = listAllCategoriesUseCase;
    }

    @GetMapping
    public List<CategoryOutput> getCategories() {
        return listAllCategoriesUseCase.execute();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryOutput createCategory(PersistCategoryInput input) {
        return persistCategoryUseCase.execute(input);
    }
}
