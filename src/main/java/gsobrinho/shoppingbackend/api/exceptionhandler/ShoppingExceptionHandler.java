package gsobrinho.shoppingbackend.api.exceptionhandler;

import gsobrinho.shoppingbackend.domain.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import gsobrinho.shoppingbackend.domain.exception.EntityNotFoundException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class ShoppingExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> businessExceptionHandler(
            final EntityNotFoundException ex, final WebRequest request){
        final HttpStatus status = HttpStatus.BAD_REQUEST;

        final ErrorResponse errorBody = ErrorResponse.builder()
                .status(status.value())
                .title(status.getReasonPhrase())
                .detail(ex.getMessage())
                .dateTime(LocalDateTime.now())
                .build();

        return handleExceptionInternal(ex, errorBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> entityNotFoundExceptionHandler(
            final EntityNotFoundException ex, final WebRequest request){
        final HttpStatus status = HttpStatus.NOT_FOUND;

        final ErrorResponse errorBody = ErrorResponse.builder()
                .status(status.value())
                .title("Entity not found")
                .detail(ex.getMessage())
                .dateTime(LocalDateTime.now())
                .build();

        return handleExceptionInternal(ex, errorBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(final Exception ex, final Object body,
            final HttpHeaders headers, final HttpStatusCode statusCode, final WebRequest request) {

        Object errorBody = body;
        if(Objects.isNull(body))
            errorBody = ErrorResponse.builder()
                .status(statusCode.value())
                .title(statusCode.toString())
                .detail(ex.getMessage())
                .dateTime(LocalDateTime.now())
                .build();
        else if(body instanceof String)
            errorBody = ErrorResponse.builder()
                .status(statusCode.value())
                .title(statusCode.toString())
                .detail(body.toString())
                .dateTime(LocalDateTime.now())
                .build();

        return super.handleExceptionInternal(ex, errorBody, headers, statusCode, request);
    }
}
