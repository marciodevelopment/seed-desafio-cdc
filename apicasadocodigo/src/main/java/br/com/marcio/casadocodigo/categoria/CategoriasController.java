package br.com.marcio.casadocodigo.categoria;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class CategoriasController {
	private CategoriaRepository categoriaRepository;
	
	@PostMapping(value = "categorias")
	public void novaCategoria(@RequestBody @Valid CategoriaRequest categoriaRequest) {
		categoriaRepository.save(categoriaRequest.toEntity());
	}
}
