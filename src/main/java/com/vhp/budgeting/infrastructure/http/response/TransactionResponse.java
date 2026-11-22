package com.vhp.budgeting.infrastructure.http.response;

import com.vhp.budgeting.application.output.TransactionOutput;

import java.math.BigDecimal;

public record TransactionResponse(String id, String description, String category, BigDecimal amount) {

    public static TransactionResponse from(TransactionOutput output) {
        return new TransactionResponse(
                output.id(),
                output.description(),
                output.category(),
                output.amount()
        );
    }
}
