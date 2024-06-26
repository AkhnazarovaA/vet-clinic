package kz.alabs.vetclinic.core.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import static kz.alabs.vetclinic.core.util.Constants.TRACE;
import static kz.alabs.vetclinic.core.util.Constants.VALIDATION_ERROR;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${exception.trace:false}")
    private boolean printStackTrace;

    private final MessageSource messageSource;

    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request
    ) {
        log.error("Method argument is not valid", ex);
        final ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                getMessageMap(VALIDATION_ERROR)
        );
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    @ExceptionHandler(NoSuchElementFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNoSuchElementFoundException(
            NoSuchElementFoundException ex,
            WebRequest request
    ) {
        log.error("Object has not found", ex);
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ElementAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleElementAlreadyExistsException(
            ElementAlreadyExistsException ex,
            WebRequest request
    ) {
        log.error("Object is already existed", ex);
        return buildErrorResponse(ex, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<Object> buildErrorResponse(
            Exception ex,
            HttpStatusCode statusCode,
            WebRequest request
    ) {
        return buildErrorResponse(ex, ex.getMessage(), statusCode, request);
    }

    private ResponseEntity<Object> buildErrorResponse(
            Exception ex,
            String message,
            HttpStatusCode statusCode,
            WebRequest request
    ) {
        final ErrorResponse errorResponse = new ErrorResponse(statusCode.value(), getMessageMap(message));
        if (printStackTrace && isTraceOn(request)) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(ex));
        }
        return ResponseEntity.status(statusCode).body(errorResponse);
    }

    private boolean isTraceOn(WebRequest request) {
        String[] value = request.getParameterValues(TRACE);
        return Objects.nonNull(value)
                && value.length > 0
                && value[0].contentEquals("true");
    }

    private Map<String, String> getMessageMap(String message, Object... args) {
        return Map.of(
                "ru", messageSource.getMessage(message, args, Locale.forLanguageTag("ru")),
                "kk", messageSource.getMessage(message, args, Locale.forLanguageTag("kk")),
                "en", messageSource.getMessage(message, args, Locale.forLanguageTag("en"))
        );
    }



    @Override
    public ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request
    ) {
        return buildErrorResponse(ex, statusCode, request);
    }

}
