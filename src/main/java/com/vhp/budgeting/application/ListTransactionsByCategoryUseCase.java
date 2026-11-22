package com.vhp.budgeting.application;

import com.vhp.budgeting.application.output.TransactionOutput;
import com.vhp.budgeting.domain.Category;
import com.vhp.budgeting.domain.CategoryRepository;
import com.vhp.budgeting.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTransactionsByCategoryUseCase {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public ListTransactionsByCategoryUseCase(TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    @Tool(name = "list-transactions-by-category", description = "Lista transações financeiras por categoria")
    public List<TransactionOutput> execute(@ToolParam(description = "Categoria de uma transação financeira") String categoryName) {
        var category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException(String.format("A categoria '%s' não foi encontrada!", categoryName)));

        return transactionRepository.findAllByCategory(category.getId())
                .stream()
                .map(TransactionOutput::from)
                .toList();
    }
}
