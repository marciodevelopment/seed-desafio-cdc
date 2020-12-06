package br.com.marcio.casadocodigo.compartilhado;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class ValidationErrorHandler {
	private final MessageSource messageSource;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ValidationErrosOutputDto handleVadationError(MethodArgumentNotValidException exception) {
		List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
		List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();
		return buildValidationErrors(globalErrors, fieldErros);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	public ValidationErrosOutputDto handleEntityNotFoundException(EntityNotFoundException exception) {
		return buildValidationErrors(exception.getMessage());
	}

	private ValidationErrosOutputDto buildValidationErrors(String message) {
		ValidationErrosOutputDto validationErros = new ValidationErrosOutputDto();
		validationErros.addError(message);
		return validationErros;
	}
	
	private ValidationErrosOutputDto buildValidationErrors(List<ObjectError> globalErrors, List<FieldError> fieldErros) {
		ValidationErrosOutputDto validationErros = new ValidationErrosOutputDto();
		globalErrors.forEach(error -> validationErros.addError(getErrorMessage(error)));
	
		fieldErros.forEach(error -> {
			String errorMessage = getErrorMessage(error);
			validationErros.addFieldError(error.getField(), errorMessage);
		});
		return validationErros;
	}

	private String getErrorMessage(ObjectError error) {
		return messageSource.getMessage(error, LocaleContextHolder.getLocale());
	}
}
