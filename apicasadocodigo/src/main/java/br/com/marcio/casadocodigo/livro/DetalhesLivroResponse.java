package br.com.marcio.casadocodigo.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;

@Getter
public class DetalhesLivroResponse {

	private AutorReponseDetalheLivro autor;
	private String titulo;
	private String resumo;
	private BigDecimal preco;
	private Integer numeroPaginas;
	private LocalDate dataPublicacao;
	private String sumario;
	

	public DetalhesLivroResponse(Livro livro) {
		titulo = livro.getTitulo();
		resumo = livro.getResumo();
		preco = livro.getPreco();
		autor = new AutorReponseDetalheLivro(livro.getAutor());
		numeroPaginas = livro.getNumeroPaginas();
		dataPublicacao = livro.getDataPublicacao();
		sumario = livro.getSumario();
	}
	
}
