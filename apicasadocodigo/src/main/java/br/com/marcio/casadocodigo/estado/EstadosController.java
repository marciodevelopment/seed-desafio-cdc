package br.com.marcio.casadocodigo.estado;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcio.casadocodigo.pais.PaisRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EstadosController {
	private final PaisRepository paisRepository;
	private final EstadoRepository estadoRepository;

	@PostMapping(value = "estados")
	public void post(@RequestBody @Valid NovoEstadoRequest novoEstado) {
		Estado estado = novoEstado.toEntity(paisRepository);
		estadoRepository.save(estado);
	}

}
