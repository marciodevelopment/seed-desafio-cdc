package br.com.marcio.casadocodigo.cupom;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.ToString;

@ToString
@Entity
public class Cupom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String codigoCupom;
	@Getter
	private @Max(100) @Positive @NotNull BigDecimal percentualDesconto;
	@Getter
	private @Future @NotNull LocalDate dataValidade;

	protected Cupom() {
		
	}
	
	public Cupom(@NotBlank String codigoCupom, @Max(100) @Positive @NotNull BigDecimal percentualDesconto,
			@Future @NotNull LocalDate dataValidade) {
		this.codigoCupom = codigoCupom;
		this.percentualDesconto = percentualDesconto;
		this.dataValidade = dataValidade;
	}

	public boolean valido() {
		return LocalDate.now().compareTo(this.dataValidade) <= 0;
	}

}
