package br.com.marcio.casadocodigo.estado;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.marcio.casadocodigo.compartilhado.annotation.UniqueValue;
import br.com.marcio.casadocodigo.pais.PaisRepository;
import lombok.Setter;

@Setter
public class NovoEstadoRequest {
	@UniqueValue(domainClass = Estado.class, fieldName = "nome", message = "Já existe um estado cadastrado com este nome")
	@NotBlank
	private String nome;
	@NotNull(message = "Id do país")
	private Long idPais;
	
	public Estado toEntity(PaisRepository paisRepository) {
		return new Estado(paisRepository.findById(this.idPais).get(), nome);
	}
}
