package br.com.marcio.casadocodigo.cupom;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CupomTest {

	@ParameterizedTest
	@CsvSource({
		"0, true",
		"-1, false",
		"1, true",
		"-10, false"
	})
	void test1(long valor, boolean resultado) {
		Cupom cupom = new Cupom("", null, LocalDate.now().plusDays(valor));
		Assertions.assertEquals(resultado, cupom.valido());
	}

}
