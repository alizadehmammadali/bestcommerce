package az.ibar.bcmsapigateway.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<?> handlerResourceAlreadyExistsException(ResourceAlreadyExistsException e,
                                                                   WebRequest webRequest) {
        log.error(e.getMessage());
        RestResponse restResponse = RestResponse.builder()
                .message(e.getMessage())
                .path(getPath(webRequest))
                .status(HttpStatus.CONFLICT.value())
                .build();
        return new ResponseEntity<>(restResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException e,
                                                              WebRequest webRequest) {
        log.error(e.getMessage());
        RestResponse restResponse = RestResponse.builder()
                .message(e.getMessage())
                .path(getPath(webRequest))
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(
            ConstraintViolationException ex) {
        RestResponse response = new RestResponse(BAD_REQUEST);
        response.setMessage("Constraint Validation error");
        response.addValidationErrors(ex.getConstraintViolations());
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        RestResponse response = new RestResponse(BAD_REQUEST);
        response.setMessage("Data validation error");
        response.addValidationErrors(ex.getBindingResult().getFieldErrors());
        response.addValidationError(ex.getBindingResult().getGlobalErrors());
        return ResponseEntity.badRequest().body(response);
    }

    @Override
    public ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        return ResponseEntity.badRequest().body(new RestResponse(BAD_REQUEST, error, ex));
    }

    @Override
    public ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(new RestResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), ex));
    }


    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
        String error = "Malformed JSON request";
        ex.printStackTrace();
        return ResponseEntity.badRequest().body(new RestResponse(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        String error = "Error writing JSON output";
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new RestResponse(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RestResponse response = new RestResponse(BAD_REQUEST);
        response.setMessage(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));
        response.setError(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                               WebRequest request) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            return ResponseEntity.status(CONFLICT).body(new RestResponse(HttpStatus.CONFLICT, "Database error", ex.getCause()));
        }
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new RestResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                   WebRequest request) {
        RestResponse response = new RestResponse(BAD_REQUEST);
        response.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
        response.setError(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex,
                                                                   WebRequest request) {
        RestResponse response = new RestResponse(BAD_REQUEST);
        response.setMessage(ex.getMessage());
        response.setError("Validation exception");
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex,
                                                                   WebRequest request) {
        RestResponse response = new RestResponse(BAD_REQUEST);
        response.setMessage(ex.getMessage());
        response.setError("Credentials exception");
        return ResponseEntity.badRequest().body(response);
    }

    private String getPath(WebRequest request) {
        return request.getDescription(false).substring(4);
    }

}

