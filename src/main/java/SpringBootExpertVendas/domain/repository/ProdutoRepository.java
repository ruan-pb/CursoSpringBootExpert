package SpringBootExpertVendas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/*
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
*/
import org.springframework.stereotype.Repository;

import SpringBootExpertVendas.domain.Cliente;
import SpringBootExpertVendas.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	/*

	List<Cliente> findByNomeLike(String nome);

	List<Cliente> findByNomeOrId(String nome, Integer id);

	Cliente findOneByNome(String nome);

	boolean existsByNome(String nome);

	@Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
	List<Cliente> encontrarPorNome(@Param("nome") String nome);

	@Query(" delete from Cliente c where c.nome =:nome ")
	@Modifying
	void deleteByNome(String nome);
*/
}

