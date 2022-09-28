package br.com.bank.digital_account.web.dto.request

data class TransferReqWebDTO(
    val origin: Long,
    val destination: Long,
    val money: Double
)
