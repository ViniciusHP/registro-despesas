package com.vhp.budgeting.infrastructure.persistence.entity;

import com.vhp.budgeting.domain.Category;
import com.vhp.budgeting.domain.CategoryId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    @Id
    private UUID id;

    private String name;

    public static CategoryEntity from(Category category) {
        return new CategoryEntity(
                category.getId().uuid(),
                category.getName()
        );
    }

    public Category toDomain() {
        return new Category(
                new CategoryId(this.id),
                this.name
        );
    }
}
