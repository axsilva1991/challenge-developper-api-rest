package br.com.bank.digital_account.repository.entity

import java.time.LocalDateTime
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
data class StatementEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @ManyToOne
    var destinationAccount: AccountEntity? = null,
    @ManyToOne
    var originAccount: AccountEntity? = null,
    var type: String = "",
    var value: Double = 0.0,
    var balance: Double = 0.0,
    var operationDate: LocalDateTime = LocalDateTime.now()
)