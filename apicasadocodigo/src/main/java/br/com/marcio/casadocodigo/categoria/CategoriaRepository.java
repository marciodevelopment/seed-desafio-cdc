package br.com.marcio.casadocodigo.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	boolean existsByNome(String nome);

}
