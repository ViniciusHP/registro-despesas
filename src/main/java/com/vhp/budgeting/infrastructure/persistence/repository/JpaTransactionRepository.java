package com.vhp.budgeting.infrastructure.persistence.repository;

import com.vhp.budgeting.domain.CategoryId;
import com.vhp.budgeting.domain.Transaction;
import com.vhp.budgeting.domain.TransactionRepository;
import com.vhp.budgeting.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaTransactionRepository implements TransactionRepository {

    private final TransactionEntityRepository transactionEntityRepository;

    public JpaTransactionRepository(TransactionEntityRepository transactionEntityRepository) {
        this.transactionEntityRepository = transactionEntityRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = TransactionEntity.from(transaction);
        return transactionEntityRepository.save(entity).toDomain();
    }

    @Override
    public List<Transaction> findAllByCategory(CategoryId categoryId) {
        return transactionEntityRepository.findAllByCategory(categoryId.uuid())
                .stream()
                .map(TransactionEntity::toDomain)
                .toList();
    }
}
