package br.com.bank.digital_account.web.dto.response

import java.time.LocalDateTime

data class AccountResWebDTO(
    val code: Long,
    val holder: HolderResWebDTO,
    val agency: Long,
    val balance: Double,
    val situationDate: LocalDateTime,
    val active:Boolean
)