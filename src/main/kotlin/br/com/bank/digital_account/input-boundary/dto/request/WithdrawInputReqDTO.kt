package br.com.bank.digital_account.`input-boundary`.dto.request

data class WithdrawInputReqDTO(
    val accountCode: Long,
    val money: Double
)