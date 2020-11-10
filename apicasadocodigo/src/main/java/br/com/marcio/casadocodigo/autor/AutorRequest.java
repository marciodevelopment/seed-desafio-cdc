package br.com.marcio.casadocodigo.autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.marcio.casadocodigo.compartilhado.annotation.UniqueValue;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class AutorRequest {
	@Size(min = 1, max = 255)
	@NotBlank
	@Email
	@UniqueValue(domainClass = Autor.class, fieldName = "email")
	private String email;
	
	@Size(min = 0, max = 255)
	@NotBlank
	private String nome;
	
	@Size(min = 0, max = 400)
	@NotBlank
	private String descricao;
	
	
	public Autor toEntity() {
		return new Autor(nome, email, descricao);
	}
}
