package br.com.bank.digital_account.web

import br.com.bank.digital_account.`input-boundary`.AccountInput
import br.com.bank.digital_account.web.dto.request.AccountHolderWebDTO
import br.com.bank.digital_account.web.dto.request.DepositReqWebDTO
import br.com.bank.digital_account.web.dto.request.TransferReqWebDTO
import br.com.bank.digital_account.web.dto.request.WithdrawReqWebDTO
import br.com.bank.digital_account.web.dto.response.AccountResWebDTO
import br.com.bank.digital_account.web.dto.response.EntriesResWebDTO
import br.com.bank.digital_account.web.dto.response.EntriesResWebDTOMapper.Companion.mapperFrom
import br.com.bank.digital_account.web.mapper.AccountResWebDTOMapper.Companion.mapperFrom
import br.com.bank.digital_account.web.mapper.DepositInputReqDTOMapper.Companion.mapperFrom
import br.com.bank.digital_account.web.mapper.TransferInputReqDTOMapper.Companion.mapperFrom
import br.com.bank.digital_account.web.mapper.WithdrawInputReqDTOMapper.Companion.mapperFrom
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(
    private val accountInput: AccountInput? = null,
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/v1/account")
    fun open(@RequestBody accountHolderWebDTO: AccountHolderWebDTO): ResponseEntity<HttpStatus> {
        log.info("AccountController.open() = AccountHolderWebDTO:-> {}", accountHolderWebDTO)
        accountInput?.open(name = accountHolderWebDTO.name, documentCode = accountHolderWebDTO.documentNumber)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PutMapping("/v1/account/situation/{code}")
    fun situation(
        @PathVariable("code") code: Long,
        @RequestHeader("active") isActive: Boolean
    ): ResponseEntity<AccountResWebDTO> {
        log.info("AccountController.situation() = code:-> {}", code, "active:-> {}", isActive)
        return ResponseEntity(accountInput?.situationStatus(code, isActive)!!.mapperFrom(), HttpStatus.OK)
    }

    @GetMapping("/v1/account/{documentNumber}")
    fun accountBy(@PathVariable("documentNumber") documentNumber: String): ResponseEntity<AccountResWebDTO> {
        log.info("AccountController.accountBy() = documentNumber:-> {}", documentNumber)
        val account = accountInput?.findBy(documentCode = documentNumber)!!.mapperFrom()
        return ResponseEntity(account, HttpStatus.OK)
    }

    @PostMapping("/v1/account/deposit")
    fun deposit(@RequestBody depositReqWebDTO: DepositReqWebDTO): ResponseEntity<HttpStatus> {
        log.info("AccountController.deposit() = DepositReqWebDTO:-> {}", depositReqWebDTO)
        accountInput?.deposit(depositReqWebDTO.mapperFrom())
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PostMapping("/v1/account/transfer")
    fun transfer(@RequestBody transferReqWebDTO: TransferReqWebDTO): ResponseEntity<HttpStatus> {
        log.info("AccountController.transfer() = TransferReqWebDTO:-> {}", transferReqWebDTO)
        accountInput?.transfer(transferReqWebDTO.mapperFrom())
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PostMapping("/v1/account/withdraw")
    fun withdraw(@RequestBody withdrawReqWebDTO: WithdrawReqWebDTO): ResponseEntity<HttpStatus> {
        log.info("AccountController.withdraw() = WithdrawReqWebDTO:-> {}", withdrawReqWebDTO)
        accountInput?.withdraw(withdrawReqWebDTO.mapperFrom())
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping("/v1/account/statement/{code}")
    fun statement(
        @PathVariable("code") code: Long,
        @RequestHeader("days") days: Long
    ): ResponseEntity<List<EntriesResWebDTO>> {
        log.info("AccountController.statement() = code:-> {}", code)
        val account = accountInput?.statement(code = code, days = days)!!.mapperFrom()
        return ResponseEntity(account, HttpStatus.OK)
    }

    @DeleteMapping("/v1/account/{code}")
    fun cancel(
        @PathVariable("code") code: Long
    ): ResponseEntity<AccountResWebDTO> {
        log.info("AccountController.cancel() = code:-> {}", code)
        accountInput?.cancel(code)
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }
}

