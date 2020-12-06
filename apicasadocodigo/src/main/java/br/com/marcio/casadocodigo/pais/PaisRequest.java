package br.com.marcio.casadocodigo.pais;

import javax.validation.constraints.NotBlank;

import br.com.marcio.casadocodigo.compartilhado.annotation.UniqueValue;
import lombok.Setter;


@Setter
public class PaisRequest {
	@UniqueValue(domainClass = Pais.class, fieldName = "nome", message = "País já foi cadastrado")
	@NotBlank(message = "Nome")
	private String nome;

	public Pais toEntity() {
		return new Pais(nome);
	}
}
