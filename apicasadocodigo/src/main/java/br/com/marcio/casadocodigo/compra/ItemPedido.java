package br.com.marcio.casadocodigo.compra;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.marcio.casadocodigo.livro.Livro;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(of = "livro")
@Embeddable
public class ItemPedido {
	@ManyToOne
	private @NotNull Livro livro;
	private @Positive int quantidade;
	private @Positive @NotNull BigDecimal precoMomento;
	
	protected ItemPedido() {
		
	}
	
	public BigDecimal getTotal() {
		return precoMomento.multiply(new BigDecimal(quantidade));
	}

	public ItemPedido(@NotNull Livro livro, @Positive int quantidade) {
		this.livro = livro;
		this.quantidade = quantidade;
		this.precoMomento = livro.getPreco();
	}
}
