package br.com.marcio.casadocodigo.cupom;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.marcio.casadocodigo.compartilhado.annotation.UniqueValue;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class NovoCupomRequest {
	@UniqueValue(domainClass = Cupom.class, fieldName = "codigoCupom", message = "Cupom já existente na base de dados")
	@NotBlank
	private String codigoCupom;
	@Max(value = 100)
	@Positive
	@NotNull
	private BigDecimal percentualDesconto;
	@Future
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@NotNull
	private LocalDate dataValidade;
	
	public Cupom toEntity() {
		return new Cupom(codigoCupom, percentualDesconto, dataValidade);
	}
}
