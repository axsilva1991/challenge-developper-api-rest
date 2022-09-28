package br.com.bank.digital_account.domain.constants

class Constants {
    companion object {
        const val DOCUMENTINVALIDMESSAGE = "Document isn't valid from account holder."
        const val AMMOUNTTRANSACTIONISNOTOK = "Please check the amount for this transaction."
        const val AMMOUNTISNOTOK = "Please check the amount selected."
        const val ACCOUNTCODEISNOTOK = "Please check the account code."
        const val INSUFFICIENT_BALANCE = "Your balance is insufficient for this operation."
        const val VALUEISNOTALLOWED = "Value not allowed for this operation"
        const val PERIODINVALID = "Select a period less than or equal to thirty days."
    }
}