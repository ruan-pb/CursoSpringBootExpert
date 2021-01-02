package SpringBootExpertVendas.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SpringBootExpertVendas.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>  {

	
	Optional<Usuario>findByLogin(String login);
}
