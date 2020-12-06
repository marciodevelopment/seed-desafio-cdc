package br.com.marcio.casadocodigo.pais;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PaisesController {
	private final PaisRepository paisRepository;

	@PostMapping(value = "paises")
	public void post(@RequestBody @Valid PaisRequest pais) {
		Pais novoPais = pais.toEntity();
		paisRepository.save(novoPais);
	}

}
