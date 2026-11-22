package com.vhp.budgeting.application.output;

import com.vhp.budgeting.domain.Category;

public record CategoryOutput(String id, String name) {

    public static CategoryOutput from(Category category) {
        return new CategoryOutput(category.getId().uuid().toString(), category.getName());
    }
}
