package br.com.marcio.casadocodigo.autor;



import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AutoresController {
	// 1
	private final AutorRepository autorRepository;
	
	
	@PostMapping(value = "/autores")
	// AutorResponse -> 2
	// autorRequest  -> 3
	public AutorResponse postMethodName(@Valid @RequestBody AutorRequest autorRequest) {
		Autor novoAutor = autorRequest.toEntity();
		autorRepository.save(novoAutor);
		return new AutorResponse(novoAutor);
	}

}
