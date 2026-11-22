package com.vhp.budgeting.application;

import com.vhp.budgeting.application.input.PersistTransactionInput;
import com.vhp.budgeting.application.output.TransactionOutput;
import com.vhp.budgeting.domain.CategoryRepository;
import com.vhp.budgeting.domain.Transaction;
import com.vhp.budgeting.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class PersistTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public PersistTransactionUseCase(TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    @Tool(name = "persist-transaction", description = "Persiste uma nova transação financeira")
    public TransactionOutput execute(PersistTransactionInput input) {
        var category = categoryRepository.findByName(input.category())
                .orElseThrow(() -> new RuntimeException(String.format("A categoria '%s' não foi encontrada!", input.category())));

        var transaction = new Transaction(input.description(), input.amount(), category);
        var savedTransaction = transactionRepository.save(transaction);
        return TransactionOutput.from(savedTransaction);
    }
}
