package br.com.marcio.casadocodigo.cupom;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CuponsController {
	private final EntityManager manager;
	
	@Transactional
	@PostMapping(value = "cupons")
	public void post(@RequestBody @Valid NovoCupomRequest novoCupomRequest) {
		Cupom novoCupom = novoCupomRequest.toEntity();
		manager.persist(novoCupom);
	}

}
