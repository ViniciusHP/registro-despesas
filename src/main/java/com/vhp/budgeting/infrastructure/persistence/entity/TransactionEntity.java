package com.vhp.budgeting.infrastructure.persistence.entity;

import com.vhp.budgeting.domain.Transaction;
import com.vhp.budgeting.domain.TransactionId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    private UUID id;

    private String description;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;


    public static TransactionEntity from(Transaction transaction) {
        return new TransactionEntity(
                transaction.getId().uuid(),
                transaction.getDescription(),
                transaction.getAmount(),
                CategoryEntity.from(transaction.getCategory())
        );
    }

    public Transaction toDomain() {
        return new Transaction(
                new TransactionId(this.id),
                this.description,
                this.amount,
                category.toDomain()
        );
    }
}
