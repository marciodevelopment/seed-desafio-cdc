package br.com.marcio.casadocodigo.estado;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EstadoPertenceAPaisValidator implements Validator {
	private final EstadoRepository estadoRepository; 
	
	@Override
	public boolean supports(Class<?> clazz) {
		return EstadoPais.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		EstadoPais estadoPaisValidator = (EstadoPais) target;
		
		if(naoExecutarValidacao(estadoPaisValidator, errors)) {
			return;
		}
		boolean estadoPertenceAoPais = estadoRepository.existsByIdAndPais_id(estadoPaisValidator.getIdEstado(), estadoPaisValidator.getIdPais());
		if (!estadoPertenceAoPais) {
			errors.rejectValue("idEstado", "null", "O estado não pertence ao pais selecionado.");
		}
	}

	private boolean naoExecutarValidacao(EstadoPais estadoPaisValidator, Errors errors) {
		if (errors.hasErrors()) {
			return true;
		}
		return estadoPaisValidator.getIdEstado() == null || estadoPaisValidator.getIdPais() == null;
	}
	
}
