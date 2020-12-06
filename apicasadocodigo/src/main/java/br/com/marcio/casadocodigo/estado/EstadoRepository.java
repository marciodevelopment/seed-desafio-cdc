package br.com.marcio.casadocodigo.estado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

	boolean existsByIdAndPais_id(Long idEstado, Long idPais);

}
