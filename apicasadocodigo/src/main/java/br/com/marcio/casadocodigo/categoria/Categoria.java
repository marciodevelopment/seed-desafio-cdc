package br.com.marcio.casadocodigo.categoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.ToString;

@ToString
@Entity
public class Categoria {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique=true) 
	private @NotBlank @Size(max = 255) String nome;

	public Categoria(@NotBlank @Size(max = 255) String nome) {
		this.nome = nome;
	}

}
