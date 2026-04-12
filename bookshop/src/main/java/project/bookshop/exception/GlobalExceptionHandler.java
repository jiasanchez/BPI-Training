package project.bookshop.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import project.bookshop.dto.ErrorResponseDTO;


@RestControllerAdvice
public class GlobalExceptionHandler {
private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	//Get User
	private String getUser() {
		try {
			return org.springframework.security.core.context.SecurityContextHolder
					.getContext()
					.getAuthentication()
					.getName();
		} catch (Exception e) {
			return "anonymous";
		}
	}
	
	//Global Handler for JSON Response
	private ResponseEntity<ErrorResponseDTO> jsonResponse(HttpStatus status, String message, String path){
		ErrorResponseDTO errorDTO = new ErrorResponseDTO(status.value(), message, path);
		return new ResponseEntity<>(errorDTO, status);
	}
	
	//Input Validation 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDTO> ValidationHandler(MethodArgumentNotValidException ex,
															HttpServletRequest request) {
		String errorMessage = ex.getBindingResult().getFieldErrors()
				.stream()
				.findFirst()
				.map(error -> error.getDefaultMessage())
				.orElse("Invalid input");
		logger.warn("User {} Validation Error: {} | Path {} ", getUser(), errorMessage, request.getRequestURI());
		return jsonResponse(HttpStatus.BAD_REQUEST, errorMessage, request.getRequestURI());
	}
	
	 // 400 Bad JSON
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidFormat(HttpMessageNotReadableException ex,
                                                             HttpServletRequest request) {
        String message = "Invalid input: check field values";
        logger.warn("User {} Malformed JSON | path={}", getUser(), request.getRequestURI());
        return jsonResponse(HttpStatus.BAD_REQUEST, message, request.getRequestURI());
    }

    // 404 Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(ResourceNotFoundException ex,
                                                        HttpServletRequest request) {
        logger.warn("User {} Resource not found: {} | path={}", getUser(), ex.getMessage(), request.getRequestURI());
        return jsonResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
    }

    // 409 Duplicate / Business rule
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponseDTO> handleConflict(DuplicateResourceException ex,
                                                        HttpServletRequest request) {
        logger.warn("User {} Conflict: {} | path={}", getUser(), ex.getMessage(), request.getRequestURI());
        return jsonResponse(HttpStatus.CONFLICT, ex.getMessage(), request.getRequestURI());
    }
    
    @ExceptionHandler(BookDeletionNotAllowedException.class)
    public ResponseEntity<ErrorResponseDTO> handleBookDeletion(BookDeletionNotAllowedException ex,
    													HttpServletRequest request){
    	return jsonResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI());					
    }

    // 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleAll(Exception ex,
                                                   HttpServletRequest request) {
        logger.error("User {} Unexpected error | path={}", getUser(), request.getRequestURI(), ex);
        return jsonResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", request.getRequestURI());
    }
}
