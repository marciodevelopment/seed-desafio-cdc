package br.com.marcio.casadocodigo.livro;

import br.com.marcio.casadocodigo.autor.Autor;
import lombok.Getter;

@Getter
public class AutorReponseDetalheLivro {

	private String nome;
	private String descricao;

	public AutorReponseDetalheLivro(Autor autor) {
		nome = autor.getNome();
		descricao = autor.getDescricao();
	}

}
