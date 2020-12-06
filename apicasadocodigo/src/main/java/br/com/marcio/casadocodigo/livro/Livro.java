package br.com.marcio.casadocodigo.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.marcio.casadocodigo.autor.Autor;
import br.com.marcio.casadocodigo.categoria.Categoria;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
@ToString
@Getter
@Entity
@EqualsAndHashCode(of = "isbn")
public class Livro {
	@GeneratedValue
	@Id
	private Long id;
	private @NotBlank @Size(max = 255) String titulo;
	private @NotBlank @Size(max = 500) String resumo;
	private String sumario;
	private @NotNull @Min(20) BigDecimal preco;
	private @NotNull @Min(100) Integer numeroPaginas;
	private @Size(max = 255) @NotBlank String isbn;
	private @Future LocalDate dataPublicacao;
	@ManyToOne
	private @NotNull Categoria categoria;
	@ManyToOne
	private @NotNull Autor autor;

	protected Livro() {}

	public Livro(@NotBlank @Size(max = 255) String titulo, @NotBlank @Size(max = 500) String resumo, String sumario,
			@NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) Integer numeroPaginas,
			@Size(max = 255) @NotBlank String isbn, @Future LocalDate dataPublicacao, 
			@NotNull Categoria categoria,
			@NotNull Autor autor) {
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
		this.categoria = categoria;
		this.autor = autor;
	};


}
