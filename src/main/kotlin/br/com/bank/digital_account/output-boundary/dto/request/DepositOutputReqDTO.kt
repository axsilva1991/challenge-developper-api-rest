package br.com.bank.digital_account.`output-boundary`.dto.request

data class DepositOutputReqDTO(
    val accountCode: Long,
    val money: Double
)