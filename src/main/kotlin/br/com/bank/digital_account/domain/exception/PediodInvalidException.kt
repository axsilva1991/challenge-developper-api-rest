package br.com.bank.digital_account.domain.exception

import java.lang.RuntimeException

class PediodInvalidException(override val message: String?) : RuntimeException(message)