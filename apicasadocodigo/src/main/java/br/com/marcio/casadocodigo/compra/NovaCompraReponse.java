package br.com.marcio.casadocodigo.compra;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class NovaCompraReponse {
	private BigDecimal valorCompra;
	private BigDecimal valorDesconto;
	private BigDecimal valorFinal;
	
	public NovaCompraReponse(Compra novaCompra) {
		
	}

}
