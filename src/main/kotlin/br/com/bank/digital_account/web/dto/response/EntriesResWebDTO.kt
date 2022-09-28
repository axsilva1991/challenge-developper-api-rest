package br.com.bank.digital_account.web.dto.response

import java.time.LocalDateTime

data class EntriesResWebDTO (
    val type: String,
    val value: Double,
    val operation_date: LocalDateTime
)