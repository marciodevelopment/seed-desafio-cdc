package br.com.marcio.casadocodigo.compra;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.marcio.casadocodigo.cupom.Cupom;

@Embeddable
public class CupomAplicado {
	@ManyToOne
	@NotNull
	private Cupom cupom;
	@NotNull
	@Positive
	private BigDecimal percentualDesconto;
	@NotNull
	@FutureOrPresent
	private LocalDate dataValidade;
	
	protected CupomAplicado() {
		
	}
	
	public CupomAplicado(Cupom cupom) {
		this.cupom = cupom;
		this.percentualDesconto = cupom.getPercentualDesconto();
		dataValidade = cupom.getDataValidade();
		
	}
}
