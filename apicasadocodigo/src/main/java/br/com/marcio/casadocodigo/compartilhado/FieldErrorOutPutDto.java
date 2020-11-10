package br.com.marcio.casadocodigo.compartilhado;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FieldErrorOutPutDto {
	private final String field;
	private final String errorMessage;
}
