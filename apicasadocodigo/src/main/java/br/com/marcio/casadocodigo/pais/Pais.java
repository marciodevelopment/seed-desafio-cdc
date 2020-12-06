package br.com.marcio.casadocodigo.pais;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Entity
public class Pais {
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	@NotBlank(message = "Nome")
	private String nome;
	
	protected Pais() {
		
	}
	
	public Pais(@NotBlank(message = "Nome") String nome) {
		this.nome = nome;
	}
	
}
