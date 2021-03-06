package br.com.marcio.casadocodigo.compra;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import lombok.ToString;

@Entity
@ToString(exclude = {"compra"})
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private @NotNull @Valid Compra compra;
	@ElementCollection
	private @Size(min = 1) Set<ItemPedido> itens = new HashSet<>(1);

	public Pedido(@NotNull @Valid Compra compra, @Size(min=1) Set<ItemPedido> itens) {
		Assert.isTrue(!itens.isEmpty(), "Todo pedido deve ter pelo menos um item");
		this.compra = compra;
		this.itens.addAll(itens);
	}

	public boolean totalIgual(@Positive @NotNull BigDecimal total) {
		BigDecimal totalPedido =itens
				.stream()
				.map(ItemPedido::getTotal)
				.reduce(BigDecimal.ZERO, (atual, proximo) -> atual.add(proximo));
		return totalPedido.equals(total);
	}

}
