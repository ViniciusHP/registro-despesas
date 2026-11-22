package com.vhp.budgeting.application.input;

import org.springframework.ai.tool.annotation.ToolParam;

public record PersistCategoryInput(@ToolParam(description = "Nome da categoria") String name) {
}
