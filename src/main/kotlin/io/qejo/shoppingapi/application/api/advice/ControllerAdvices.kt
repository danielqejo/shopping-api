package io.qejo.shoppingapi.application.api.advice

import io.qejo.shoppingapi.application.api.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerAdvices {

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFoundExceptionHandler(exception: NotFoundException): ResponseEntity<Error> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error(exception.message!!))
    }

}
