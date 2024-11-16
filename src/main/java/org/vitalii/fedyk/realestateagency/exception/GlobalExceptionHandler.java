package org.vitalii.fedyk.realestateagency.exception;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ConstraintViolationResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        final Map<String, List<String>> groupOfMessages = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            groupOfMessages.merge(fieldError.getField(),
                    Collections.singletonList(messageSource.getMessage(Objects.requireNonNull(fieldError.getDefaultMessage()),
                            fieldError.getArguments(),
                            LocaleContextHolder.getLocale())),
                    (e1, e2) -> {
                        List<String> list = new ArrayList<>(e1);
                        list.addAll(e2);
                        return list;
                    });
        }
        return new ConstraintViolationResponse(groupOfMessages);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundException(final NotFoundException ex) {
        return new ExceptionResponse(ex.getLocalizedMessage());
    }
}
