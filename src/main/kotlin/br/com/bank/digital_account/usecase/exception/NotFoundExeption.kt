package br.com.bank.digital_account.usecase.exception

class NotFoundExeption(override val message: String?) : RuntimeException(message)
