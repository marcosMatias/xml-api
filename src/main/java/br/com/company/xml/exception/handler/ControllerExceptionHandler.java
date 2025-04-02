package br.com.company.xml.exception.handler;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import br.com.company.xml.dto.ResponseDto;
import br.com.company.xml.exception.BusinessException;
import br.com.company.xml.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseDto handleBusinessException(BusinessException ex, WebRequest request) {
		log.error("Erro em BusinessException: {} | Request: {}", ex.getMessage(), request.getDescription(false), ex);
		return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseDto handleGlobalException(Exception ex, WebRequest request) {
		 log.error("Erro em GlobalException: {} | Request: {}", ex.getMessage(), request.getDescription(false), ex);
		return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseDto handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
	    String message = ex.getBindingResult()
	                       .getAllErrors()
	                       .stream()
	                       .map(ObjectError::getDefaultMessage)
	                       .collect(Collectors.joining("; "));
	   
	   
	    log.error("Erro em handleValidationException: {} | Request: {}", message, request.getDescription(false), ex);

	    return createErrorResponse(HttpStatus.BAD_REQUEST, message, request);
	}

	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseDto handleJsonParseException(HttpMessageNotReadableException ex, WebRequest request) {
	    
		 log.error("Erro em handleJsonParseException: {} | Request: {}", ex.getMessage(), request.getDescription(false), ex);
		 
		return createErrorResponse(HttpStatus.BAD_REQUEST, "Requisição malformada", request);
	}

	
	private ResponseDto createErrorResponse(HttpStatus status, String message, WebRequest request) {
		return ResponseDto.builder()
				          .statusCode(status.value())
				          .data(DateUtil.retornarDataHoraAtual())
				          .message(message)
				          .description(request.getDescription(false))
				          .build();
	}

}
