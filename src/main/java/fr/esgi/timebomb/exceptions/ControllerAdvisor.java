package fr.esgi.timebomb.exceptions;

import fr.esgi.timebomb.authentification.AccountWithUsernameNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceWithIdNotFoundException.class)
    protected ResponseEntity<?> resourceWithIdNotFound(ResourceWithIdNotFoundException exception, WebRequest request) {
        return handleExceptionInternal(
                exception,
                exception.getErrors(),
                new HttpHeaders(),
                NOT_FOUND,
                request
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException exception,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Could not perform SQL operation");
        errors.put("error", exception.getMessage());

        return handleExceptionInternal(exception,
                errors,
                new HttpHeaders(),
                CONFLICT,
                request
        );
    }



    @ExceptionHandler(AccountWithUsernameNotFoundException.class)
    protected ResponseEntity<?> profileWithUsernameNotFound(AccountWithUsernameNotFoundException exception,
                                                            WebRequest request) {
        return handleExceptionInternal(exception,
                exception.getErrors(),
                new HttpHeaders(),
                NOT_FOUND,
                request
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                             HttpHeaders httpHeaders,
                                                             HttpStatus httpStatus,
                                                             WebRequest request) {

        BindingResult bindingResult = exception.getBindingResult();
        List<Map<String, String>> errors = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", fieldError.getDefaultMessage());
                    response.put("object", fieldError.getObjectName());
                    response.put("code", fieldError.getCode());
                    response.put("field", fieldError.getField());
                    if (fieldError.getRejectedValue() != null) {
                        response.put("value", fieldError.getRejectedValue().toString());
                    }
                    return response;
                })
                .collect(Collectors.toList());

        log.error("Argument not valid");

        return handleExceptionInternal(exception, errors, new HttpHeaders(), BAD_REQUEST, request);
    }
}
