package br.com.marcio.casadocodigo.livro;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.marcio.casadocodigo.autor.Autor;
import br.com.marcio.casadocodigo.autor.AutorRepository;
import br.com.marcio.casadocodigo.categoria.Categoria;
import br.com.marcio.casadocodigo.categoria.CategoriaRepository;

class NovoLivroRequestTest {

	NovoLivroRequest request = new NovoLivroRequest(null, null, null, null, null, null, null, 1l, 1l);
	
	@Test
	@DisplayName("Cria o livro com categoria e autor existentes")
	void teste1() {
		AutorRepository autorRepository = Mockito.mock(AutorRepository.class);
		Mockito.when(autorRepository.findById(1l)).thenReturn(Optional.of(new Autor("", "", "")));
		
		CategoriaRepository categoriaRepository = Mockito.mock(CategoriaRepository.class);
		Mockito.when(categoriaRepository.findById(1l)).thenReturn(Optional.of(new Categoria("")));
		
		Assertions.assertNotNull(request.toEntity(autorRepository, categoriaRepository));
	}
	
	@Test
	@DisplayName("Não cria o livro com categoria inexistentes")
	void teste2() {
		AutorRepository autorRepository = Mockito.mock(AutorRepository.class);
		Mockito.when(autorRepository.findById(1l)).thenReturn(Optional.of(new Autor("", "", "")));
		
		CategoriaRepository categoriaRepository = Mockito.mock(CategoriaRepository.class);
		Mockito.when(categoriaRepository.findById(1l)).thenReturn(Optional.empty());
		
		Assertions.assertThrows(EntityNotFoundException.class, () -> request.toEntity(autorRepository, categoriaRepository));
	}
	
	
	@Test
	@DisplayName("Não cria o livro com autor inexistente")
	void teste3() {
		AutorRepository autorRepository = Mockito.mock(AutorRepository.class);
		Mockito.when(autorRepository.findById(1l)).thenReturn(Optional.empty());
		
		CategoriaRepository categoriaRepository = Mockito.mock(CategoriaRepository.class);
		Mockito.when(categoriaRepository.findById(1l)).thenReturn(Optional.of(new Categoria("")));
		
		Assertions.assertThrows(EntityNotFoundException.class, () -> request.toEntity(autorRepository, categoriaRepository));
	}

}
