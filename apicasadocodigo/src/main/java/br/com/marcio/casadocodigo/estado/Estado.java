package br.com.marcio.casadocodigo.estado;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.marcio.casadocodigo.pais.Pais;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
public class Estado {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private @NotNull Pais pais;
	private @NotBlank String nome;

	protected Estado() {
		
	}
	
	public Estado(@NotNull Pais pais, @NotBlank String nome) {
		this.pais = pais;
		this.nome = nome;
	}

}
