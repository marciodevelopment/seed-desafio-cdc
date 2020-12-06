package br.com.marcio.casadocodigo.livro;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DetalheLivrosController {

	private final LivroRepository livroRespository;

	@GetMapping(value = "/produtos/{id}")
	public DetalhesLivroResponse getMethodName(@PathVariable("id") Long cdLivro) {
		Livro livro = livroRespository
				.findById(cdLivro)
				.orElseThrow(() -> new EntityNotFoundException("Não há livro para o id enviado: " + cdLivro));
		
		
		return new DetalhesLivroResponse(livro);
	}

}
