package br.com.marcio.casadocodigo.autor;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
public class Autor {

	@Id
	@GeneratedValue
	private Long id;
	private @Size(min = 0, max = 255) @NotBlank String nome;
	@Column(unique=true)
	private @Size(min = 0, max = 255) @NotBlank String email;
	private @Size(min = 0, max = 400) @NotBlank String descricao;
	private @NotNull LocalDateTime dataRegistro;
	
	@PrePersist
	private void prePersist() {
		this.dataRegistro = LocalDateTime.now();
	}

	protected Autor() {
	}
	
	public Autor(@Size(min = 0, max = 255) @NotBlank String nome, 
			@Size(min = 0, max = 255) @NotBlank String email,
			@Size(min = 0, max = 400) @NotBlank String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

}
