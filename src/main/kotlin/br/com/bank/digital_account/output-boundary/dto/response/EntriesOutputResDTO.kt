package br.com.bank.digital_account.`output-boundary`.dto.response

import java.time.LocalDateTime

data class EntriesOutputResDTO(
    val type: String,
    val value: Double,
    val operation_date: LocalDateTime
)