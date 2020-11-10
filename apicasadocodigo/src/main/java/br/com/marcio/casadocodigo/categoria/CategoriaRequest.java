package br.com.marcio.casadocodigo.categoria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.marcio.casadocodigo.compartilhado.annotation.UniqueValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaRequest {
	@NotBlank
	@Size(max = 255)
	@UniqueValue(fieldName = "nome", domainClass = Categoria.class)
	private String nome;
	
	public Categoria toEntity() {
		return new Categoria(this.nome);
	}
}
