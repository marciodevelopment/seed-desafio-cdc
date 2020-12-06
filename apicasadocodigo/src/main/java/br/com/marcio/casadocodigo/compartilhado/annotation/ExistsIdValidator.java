package br.com.marcio.casadocodigo.compartilhado.annotation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {
	private String domainAttribute;
	private Class<?> klass;
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void initialize(ExistsId params) {
		domainAttribute = params.fieldName();
		klass = params.domainClass();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		Query query = entityManager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
		query.setMaxResults(1);
		query.setParameter("value", value);
		return !query.getResultList().isEmpty();
	}
	
}
