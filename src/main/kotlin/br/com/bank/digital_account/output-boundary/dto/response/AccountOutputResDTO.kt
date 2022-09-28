package br.com.bank.digital_account.`output-boundary`.dto.response

import java.time.LocalDateTime

data class AccountOutputResDTO(
    val code: Long,
    val holder: HolderOutputResDTO,
    val agency: Long,
    val balance: Double,
    val descriptionStatus: String,
    val situationDate: LocalDateTime,
    val active: Boolean
)