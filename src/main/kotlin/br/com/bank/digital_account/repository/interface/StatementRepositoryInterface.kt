package br.com.bank.digital_account.repository.`interface`

import br.com.bank.digital_account.repository.entity.StatementEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface StatementRepositoryInterface : JpaRepository<StatementEntity, Long> {
    fun findByOperationDateBetweenAndDestinationAccountId(
        from: LocalDateTime,
        to: LocalDateTime,
        destinationAccountId: Long
    ): List<StatementEntity>

}