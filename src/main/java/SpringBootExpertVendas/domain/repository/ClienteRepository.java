package SpringBootExpertVendas.domain.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
/*
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
*/
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import SpringBootExpertVendas.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	List<Cliente> findByNomeLike(String nome);
	
	List<Cliente> findByNomeOrId(String nome,Integer id);
	
	Cliente findOneByNome(String nome);
	
	boolean existsByyNome(String nome);
	



}


	/*
	@Autowired
	public EntityManager entityManager;
	@Transactional
    public Cliente salvar(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente atualizar(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void deletar(Cliente cliente){
        if(!entityManager.contains(cliente)){
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional
    public void deletar(Integer id){
        Cliente cliente = entityManager.find( Cliente.class, id );
        deletar(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNome(String nome){
       String jpql = " select c from Cliente c where c.nome like :nome ";
       TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
       query.setParameter("nome", "%" + nome +"%");
       return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> obterTodos(){
        return entityManager
                .createQuery("from Cliente", Cliente.class)
                .getResultList();
    }

}
	 

	
	 * private static String INSERT = "insert into cliente (nome) values (?) ";
	 * private static String SELECT_ALL = "SELECT * FROM CLIENTE "; private static
	 * String UPDATE = "update cliente set nome = ? where id = ? "; private static
	 * String DELETE = "delete from cliente where id = ? ";
	 * 
	 * @Autowired private JdbcTemplate jdbcTemplate;
	 * 
	 * public Cliente salvar(Cliente cliente){ jdbcTemplate.update( INSERT, new
	 * Object[]{cliente.getNome()} ); return cliente; }
	 * 
	 * 
	 * 
	 * public void deletar(Cliente cliente){ deletar(cliente.getId()); }
	 * 
	 * public void deletar(Integer id){ jdbcTemplate.update(DELETE, new
	 * Object[]{id}); }
	 * 
	 * public List<Cliente> buscarPorNome(String nome){ return jdbcTemplate.query(
	 * SELECT_ALL.concat(" where nome like ? "), new Object[]{"%" + nome + "%"},
	 * obterClienteMapper()); }
	 * 
	 * public List<Cliente> obterTodos(){ return jdbcTemplate.query(SELECT_ALL,
	 * obterClienteMapper()); }
	 * 
	 * private RowMapper<Cliente> obterClienteMapper() { return new
	 * RowMapper<Cliente>() {
	 * 
	 * @Override public Cliente mapRow(ResultSet resultSet, int i) throws
	 * SQLException { Integer id = resultSet.getInt("id"); String nome =
	 * resultSet.getString("nome"); return new Cliente(id, nome); } }; }
	 */

