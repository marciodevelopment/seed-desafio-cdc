package br.com.marcio.casadocodigo.livro;

import lombok.Getter;

@Getter
public class LivroResponse {
	private String titulo;
	private Long livroId;
	
	public LivroResponse(Livro livro) {
		this.titulo = livro.getTitulo();
		this.livroId = livro.getId();
	}
}
