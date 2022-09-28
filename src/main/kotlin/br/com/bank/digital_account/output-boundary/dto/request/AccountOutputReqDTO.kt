package br.com.bank.digital_account.`output-boundary`.dto.request

data class AccountOutputReqDTO(
    val holder: HolderOutputReqDTO,
    val agency: Long,
    val balance: Double
)