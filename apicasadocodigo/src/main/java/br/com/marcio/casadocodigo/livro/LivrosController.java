package br.com.marcio.casadocodigo.livro;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcio.casadocodigo.autor.AutorRepository;
import br.com.marcio.casadocodigo.categoria.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LivrosController {
	//1
	private final AutorRepository autorRepository;
	//2
	private final CategoriaRepository categoriaRepository;
	//3
	private final LivroRepository livroRepository;

	// LivroRequest -> 4
	@PostMapping(value = "livros")
	public void novoLivro(@RequestBody @Valid NovoLivroRequest livro) {
		Livro novoLivro = livro.toEntity(autorRepository, categoriaRepository);
		livroRepository.save(novoLivro);
	}

	// LivroResponse -> 5
	@GetMapping(value = "livros")
	public List<LivroResponse> getLivros() {
		return
				livroRepository
				.findAll()
				.stream()
				// 6
				.map(LivroResponse::new)
				// 7
				.collect(Collectors.toList());
	}

}
