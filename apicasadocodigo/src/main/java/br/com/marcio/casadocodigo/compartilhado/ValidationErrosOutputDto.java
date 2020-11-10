package br.com.marcio.casadocodigo.compartilhado;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationErrosOutputDto {
	private List<String> errors = new ArrayList<>(1);
	private List<FieldErrorOutPutDto>  fieldErros = new ArrayList<>(1);
	
	public void addError(String errorMessage) {
		errors.add(errorMessage);
	}

	public void addFieldError(String field, String errorMessage) {
		fieldErros.add(new FieldErrorOutPutDto(field, errorMessage));
	}

}
