package com.vhp.budgeting.application.input;

import org.springframework.ai.tool.annotation.ToolParam;

import java.math.BigDecimal;

public record PersistTransactionInput(@ToolParam(description = "Descrição do gasto") String description,
                                      @ToolParam(description = "Valor do gasto em reais, com duas casas decimais de centavos") BigDecimal amount,
                                      @ToolParam(description = "Categoria de uma transação") String category) {
}
