package br.com.bank.digital_account.`input-boundary`.dto.response

import java.time.LocalDateTime

class EntriesInputResDTO(
    val type: String,
    val value: Double,
    val operation_date: LocalDateTime
)