package br.com.marcio.casadocodigo.compra;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.marcio.casadocodigo.compartilhado.annotation.ExistsId;
import br.com.marcio.casadocodigo.livro.Livro;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NovoPedidoItemRequest {
	@NotNull
	@ExistsId(domainClass = Livro.class, fieldName = "id")
	private Long idLivro;
	
	@Positive
	private int quantidade;

	public NovoPedidoItemRequest(@NotNull Long idLivro, @Positive int quantidade) {
		super();
		this.idLivro = idLivro;
		this.quantidade = quantidade;
	}

	public ItemPedido toEntity(EntityManager manager) {
		return new ItemPedido(manager.find(Livro.class, idLivro), quantidade);
	}
	
	
}
