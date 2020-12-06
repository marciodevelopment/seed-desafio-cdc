package br.com.marcio.casadocodigo.compartilhado.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

public class CpfCnpjValidator implements ConstraintValidator<CpfCnpj, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		CNPJValidator cnpjValidator = new CNPJValidator();
		cnpjValidator.initialize(null);
		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);
		boolean isCnpjValido = cnpjValidator.isValid(value, context);
		boolean isCpfValido = cpfValidator.isValid(value, context); 		
		return isCnpjValido || isCpfValido;
		
	}
}
