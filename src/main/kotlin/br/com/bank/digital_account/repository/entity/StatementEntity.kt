package br.com.bank.digital_account.repository.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

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