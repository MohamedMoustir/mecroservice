package com.streaming.video.exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.List;
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(VideoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(VideoNotFoundException ex) {
        log.warn("Video non trouvee: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErrorResponse(LocalDateTime.now(), 404, "NOT_FOUND", ex.getMessage(), null));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult().getFieldErrors().stream()
            .map(e -> e.getField() + ": " + e.getDefaultMessage()).toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ErrorResponse(LocalDateTime.now(), 400, "VALIDATION_ERROR", "Erreur de validation", details));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        log.error("Erreur inattendue: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            new ErrorResponse(LocalDateTime.now(), 500, "INTERNAL_SERVER_ERROR", "Erreur interne", null));
    }
}