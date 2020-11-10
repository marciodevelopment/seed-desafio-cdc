package br.com.marcio.casadocodigo.autor;

import org.springframework.beans.BeanUtils;

import lombok.Data;

@Data
public class AutorResponse {
	private String email;
	private String nome;
	private String descricao;
	
	
	
	public AutorResponse(Autor autor) {
		BeanUtils.copyProperties(autor, this);
	}

}
