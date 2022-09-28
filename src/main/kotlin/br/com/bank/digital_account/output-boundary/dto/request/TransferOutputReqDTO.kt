package br.com.bank.digital_account.`output-boundary`.dto.request

data class TransferOutputReqDTO(
    val origin: Long,
    val destination: Long,
    val moneyTrasnsaction: Double,
    val originNewBalance: Double
)