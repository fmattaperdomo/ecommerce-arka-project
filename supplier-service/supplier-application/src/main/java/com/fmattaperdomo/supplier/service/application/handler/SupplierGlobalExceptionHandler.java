package com.fmattaperdomo.supplier.service.application.handler;

import com.fmattaperdomo.application.handler.ErrorDTO;
import com.fmattaperdomo.application.handler.GlobalExceptionHandler;
import com.fmattaperdomo.supplier.service.domain.exception.SupplierDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class SupplierGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {SupplierDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(SupplierDomainException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorDTO.builder().code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(exception.getMessage()).build();
    }

}
