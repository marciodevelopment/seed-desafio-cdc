package br.com.marcio.casadocodigo.cupom;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CupomValidoValidator implements Validator {
	private final CupomRepository cupomRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CupomValido.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		CupomValido cupomValido = (CupomValido) target;
		if (StringUtils.hasText(cupomValido.getCodigoCupom())) {
			Cupom cupom = cupomRepository.findByCodigoCupom(cupomValido.getCodigoCupom()).get();
			if (!cupom.valido()) {
				errors.rejectValue("CodigoCupom", "Este cupom não é mais válido");
			}
		}
	}
	
}
