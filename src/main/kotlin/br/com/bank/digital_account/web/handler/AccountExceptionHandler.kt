package br.com.bank.digital_account.web.handler

import br.com.bank.digital_account.usecase.exception.BusinessExeption
import br.com.bank.digital_account.usecase.exception.NotFoundExeption
import br.com.bank.digital_account.web.handler.dto.ErrorMessageWebDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.lang.Exception

@ControllerAdvice
class AccountExceptionHandler {
    @ExceptionHandler(value = [BusinessExeption::class])
    fun businessException(
        ex: BusinessExeption,
        request: WebRequest?
    ): ResponseEntity<ErrorMessageWebDTO?>? {
        return ResponseEntity(ErrorMessageWebDTO(ex.message.toString()), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [NotFoundExeption::class])
    fun notFoundException(
        ex: NotFoundExeption,
        request: WebRequest?
    ): ResponseEntity<HttpStatus?>? {
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [Exception::class])
    fun internalServerErrorException(
        ex: Exception,
        request: WebRequest?
    ): ResponseEntity<ErrorMessageWebDTO?>? {
        return ResponseEntity(ErrorMessageWebDTO(ex.message.toString()), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}