package com.vhp.budgeting.domain;

public class Category {
    private CategoryId id;
    private String name;

    public Category(String name) {
        this.id = new CategoryId();
        this.name = name;
    }

    public Category(CategoryId id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryId getId() {
        return id;
    }

    public void setId(CategoryId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
