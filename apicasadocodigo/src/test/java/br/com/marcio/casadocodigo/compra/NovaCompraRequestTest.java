package br.com.marcio.casadocodigo.compra;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.marcio.casadocodigo.cupom.CupomRepository;
import br.com.marcio.casadocodigo.estado.Estado;
import br.com.marcio.casadocodigo.livro.Livro;

class NovaCompraRequestTest {
	private List<NovoPedidoItemRequest> itens = new ArrayList<>();
	private NovoPedidoRequest pedido = new NovoPedidoRequest(new BigDecimal(1), itens);
	NovaCompraRequest request = new NovaCompraRequest("email", "nome", 
			"sobrenome", "documento", "endereco", "complemento", "cidade", 
			1l, 1l, "telefone", "cep", pedido , "codigoCupom");
	
	EntityManager manager = Mockito.mock(EntityManager.class);
	CupomRepository cupomRepository = Mockito.mock(CupomRepository.class);
	
	@BeforeEach
    void beforeEach() {
       itens.add(new NovoPedidoItemRequest(1l, 1));
       Mockito.when(manager.find(Livro.class, 1l)).thenReturn(new Livro("", "", "", new BigDecimal(1), 1, "isbn", LocalDate.now(), 
    		   null, null));
       
       Mockito.when(manager.find(Livro.class, 1l)).thenReturn(new Livro("", "", "", new BigDecimal(1), 1, "isbn", LocalDate.now(), 
    		   null, null));
     }
	
	@Test
	@DisplayName("Cria compra com estado e cupom")
	void test1() {
		/*
		request.setCodigoCupom("codigoCupom");
		request.setIdEstado(1l);
		Compra novaCompra = request.toEntity(manager, cupomRepository);
		
		Assertions.assertNotNull(novaCompra);
		// Indica se foi chamada o codigo
		Mockito.verify(manager).find(Estado.class, 1l);
		Mockito.verify(cupomRepository).findByCodigoCupom("codigoCupom");
		*/
	}

}
