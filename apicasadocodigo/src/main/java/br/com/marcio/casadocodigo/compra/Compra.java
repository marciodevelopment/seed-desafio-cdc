package br.com.marcio.casadocodigo.compra;

import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.marcio.casadocodigo.cupom.Cupom;
import br.com.marcio.casadocodigo.estado.Estado;
import br.com.marcio.casadocodigo.pais.Pais;
import lombok.ToString;

@ToString
@Entity
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @Email @NotBlank String email;
	private @NotBlank String nome;
	private @NotBlank String sobrenome;
	private @NotBlank String documento;
	private @NotBlank String endereco;
	private @NotBlank String complemento;
	@ManyToOne
	@NotNull
	private Pais pais;
	private @NotBlank String telefone;
	private @NotBlank String cep;
	@ManyToOne
	private Estado estado;
	@OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
	@NotNull
	private Pedido pedido;
	@Embedded
	private CupomAplicado cupomAplicado;


	protected Compra() {}

	public Compra(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento, 
			@NotNull Pais pais,
			@NotBlank String telefone, @NotBlank String cep, 
			@NotNull Function<Compra, Pedido> funcaoCriacaoDePedido) {
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.pais = pais;
		this.telefone = telefone;
		this.cep = cep;
		this.pedido = funcaoCriacaoDePedido.apply(this); 
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
		this.pais = estado.getPais();
	}

	public void aplicaCupom(Cupom cupom) {
		Assert.isTrue(cupom.valido(), "O Cupom não esta mais válido");
		Assert.isNull(cupomAplicado, "Não é possível realizar troca de cupom em uma compra");
		this.cupomAplicado = new CupomAplicado(cupom);
	}

}
