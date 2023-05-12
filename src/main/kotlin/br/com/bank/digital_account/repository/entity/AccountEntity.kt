package br.com.bank.digital_account.repository.entity

import java.time.LocalDateTime
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class AccountEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var name: String = "",
    var documentCode: String = "",
    var agency: Long = 1L,
    var balance: Double = 0.0,
    var situationDate: LocalDateTime = LocalDateTime.now(),
    var descriptionStatus:String = "created",
    var active: Boolean = true
)