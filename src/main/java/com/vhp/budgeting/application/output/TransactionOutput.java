package com.vhp.budgeting.application.output;

import com.vhp.budgeting.domain.Transaction;

import java.math.BigDecimal;

public record TransactionOutput(String id, String description, BigDecimal amount, String category) {
    public static TransactionOutput from(Transaction transaction) {
        return new TransactionOutput(
                transaction.getId().uuid().toString(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getCategory().getName());
    }
}
