package br.com.pocbackend.springboot.exception;

import java.util.Date;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    //@ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        ErrorDetails errorDetails = new ErrorDetails(new Date(), error + " " + ex.getMessage(),
                request.getDescription(false));
        return buildResponseEntity(errorDetails);
    }
    
    @ExceptionHandler(ValueParameterException.class)
    public final ResponseEntity<ErrorDetails> handleRequestParameterException(ValueParameterException ex, WebRequest request) {
      ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
          request.getDescription(false));
      return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ChargeBackException.class)
    public final ResponseEntity<ErrorDetails> handleChargeBackException(ChargeBackException ex, WebRequest request) {
      ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
          request.getDescription(false));
      return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(CodigoExistenteException.class)
    public final ResponseEntity<ErrorDetails> handleChargeBackException(CodigoExistenteException ex, WebRequest request) {
      ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
          request.getDescription(false));
      return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<Object> buildResponseEntity(ErrorDetails errorDetails) {
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
