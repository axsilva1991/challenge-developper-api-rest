package br.com.bank.digital_account.`input-boundary`.dto.response

import java.time.LocalDateTime

data class AccountInputResDTO(
    val code: Long,
    val holder: HolderInputDTO,
    val agency: Long,
    val balance: Double,
    val active: Boolean,
    val descriptionStatus:String,
    val situationDate: LocalDateTime
)