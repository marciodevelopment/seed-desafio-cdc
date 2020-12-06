package br.com.marcio.casadocodigo.compra;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NovoPedidoRequest {
	@Positive
	@NotNull
	private BigDecimal total;
	
	@Size(min = 1)
	@Valid
	private List<NovoPedidoItemRequest> itens = new ArrayList<>(1);

	public NovoPedidoRequest(@Positive @NotNull BigDecimal total, @Size(min = 1) @Valid List<NovoPedidoItemRequest> itens) {
		super();
		this.total = total;
		this.itens = itens;
	}

	public Function<Compra, Pedido> toEntity(EntityManager manager) {
		Set<ItemPedido> itensCalculados = 
				itens.stream()
				.map(item -> item.toEntity(manager))
				.collect(Collectors.toSet());
		return compra -> {
			Pedido pedido = new Pedido(compra, itensCalculados);
			Assert.isTrue(pedido.totalIgual(total), "O total enviado não corresponde ao total real.");
			return pedido;
		};
	}
	
	
}

