package com.vhp.budgeting.infrastructure.http.request;

import com.vhp.budgeting.application.input.PersistTransactionInput;

import java.math.BigDecimal;

public record TransactionRequest(String description, BigDecimal amount, String category) {

    public PersistTransactionInput toInput() {
        return new PersistTransactionInput(description, amount, category);
    }
}
