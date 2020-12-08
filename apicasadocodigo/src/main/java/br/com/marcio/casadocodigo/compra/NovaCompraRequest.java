package br.com.marcio.casadocodigo.compra;

import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;

import br.com.marcio.casadocodigo.compartilhado.annotation.CpfCnpj;
import br.com.marcio.casadocodigo.compartilhado.annotation.ExistsId;
import br.com.marcio.casadocodigo.cupom.Cupom;
import br.com.marcio.casadocodigo.cupom.CupomRepository;
import br.com.marcio.casadocodigo.cupom.CupomValido;
import br.com.marcio.casadocodigo.estado.Estado;
import br.com.marcio.casadocodigo.estado.EstadoPais;
import br.com.marcio.casadocodigo.pais.Pais;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class NovaCompraRequest implements EstadoPais, CupomValido{
	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String sobrenome;
	@CpfCnpj(message = "Documento inválido")
	@NotBlank
	private String documento;
	@NotBlank
	private String endereco;
	@NotBlank
	private String complemento;
	@NotBlank
	private String cidade;
	@Getter
	@NotNull
	@ExistsId(domainClass = Pais.class, fieldName = "id", message = "Não existe pais para o id enviado")
	private Long idPais;
	@Getter
	@ExistsId(domainClass = Estado.class, fieldName = "id", message = "Não existe estado para o id enviado")
	private Long idEstado;
	@NotBlank
	private String telefone;
	@NotBlank
	private String cep;
	@Getter
	@NotNull
	@Valid
	// 1
	private NovoPedidoRequest pedido;
	@Getter
	@ExistsId(domainClass = Cupom.class, fieldName = "codigoCupom", message = "Não existe cupom para o código enviado.")
	private String codigoCupom;
	
	
	// 1
	public Compra toEntity(EntityManager manager, CupomRepository cupomRepository) {
		// 1
		Pais pais = manager.find(Pais.class, idPais);
		// 1
		Function<Compra, Pedido> funcaoCriacaoDePedido = this.pedido.toEntity(manager);
		// 1
		Compra compra = new Compra(email, nome, sobrenome, documento, endereco, complemento,
				pais, telefone, cep, funcaoCriacaoDePedido);
		// 1		
		if (this.idEstado != null) {
			Estado estado = manager.find(Estado.class, idEstado);
			compra.setEstado(estado);
		}
		// 1
		if (StringUtils.hasText(this.codigoCupom)) {
			Cupom cupom = cupomRepository.findByCodigoCupom(this.codigoCupom).get();
			compra.aplicaCupom(cupom);
		}
		return compra;
	}
}
