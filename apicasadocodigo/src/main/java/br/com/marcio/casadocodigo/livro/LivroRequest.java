package br.com.marcio.casadocodigo.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.marcio.casadocodigo.autor.Autor;
import br.com.marcio.casadocodigo.autor.AutorRepository;
import br.com.marcio.casadocodigo.categoria.Categoria;
import br.com.marcio.casadocodigo.categoria.CategoriaRepository;
import br.com.marcio.casadocodigo.compartilhado.annotation.UniqueValue;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class LivroRequest {
	@NotBlank
	@Size(max = 255)
	@UniqueValue(domainClass = Livro.class, fieldName = "titulo", message = "Já existe um livro cadastrado com este título.")
	private String titulo;
	@NotBlank
	@Size(max = 500)
	private String resumo;

	private String sumario;
	@NotNull
	@Min(value = 20)
	private BigDecimal preco;
	@NotNull
	@Min(value = 100)
	private Integer numeroPaginas;
	@Size(max = 255)
	@NotBlank
	@UniqueValue(domainClass = Livro.class, fieldName = "isbn", message = "Já existe um livro cadastrado com este isbn.")
	private String isbn;
	@Future
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataPublicacao;
	@NotNull
	private Long idCategoria;
	@NotNull
	private Long idAutor;

	public Livro toEntity(@Valid LivroRequest livro, AutorRepository autorRepository,
			CategoriaRepository categoriaRepository) {
		Autor autor = autorRepository
				.findById(idAutor)
				.orElseThrow(() -> new EntityNotFoundException("Não há autor cadastrado para o id enviado: " + this.idAutor));

		Categoria categoria = categoriaRepository
				.findById(idCategoria)
				.orElseThrow(() -> new EntityNotFoundException("Não há categoria cadastrado para o id enviado: " + this.idCategoria));
		
		return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, categoria, autor);

	}
}
