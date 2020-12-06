package br.com.marcio.casadocodigo.cupom;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CupomRepository extends JpaRepository<Cupom, Long> {

	Optional<Cupom> findByCodigoCupom(String codigoCupom);

}
