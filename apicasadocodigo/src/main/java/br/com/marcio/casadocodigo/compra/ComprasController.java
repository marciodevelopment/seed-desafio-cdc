package br.com.marcio.casadocodigo.compra;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcio.casadocodigo.cupom.CupomRepository;
import br.com.marcio.casadocodigo.estado.EstadoPertenceAPaisValidator;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ComprasController {
	private final EntityManager manager;
	private final CupomRepository cupomRepository;
	private final EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;
	
	@InitBinder(value = "novaCompraRequest")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(estadoPertenceAPaisValidator);
	}
	
	@Transactional
	@PostMapping(value = "/compra")
	public NovaCompraReponse post(@RequestBody @Valid NovaCompraRequest novaCompraRequest) {
		Compra novaCompra = novaCompraRequest.toEntity(manager, cupomRepository);
		manager.persist(novaCompra);
		return new NovaCompraReponse(novaCompra);
	}

}
