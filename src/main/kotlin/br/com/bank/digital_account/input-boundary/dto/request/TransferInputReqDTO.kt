package br.com.bank.digital_account.`input-boundary`.dto.request

data class TransferInputReqDTO(
    val origin: Long,
    val destination: Long,
    val money: Double
)