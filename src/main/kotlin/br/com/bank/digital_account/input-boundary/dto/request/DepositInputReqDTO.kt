package br.com.bank.digital_account.`input-boundary`.dto.request

data class DepositInputReqDTO(
    val accountCode:Long,
    val money : Double
)