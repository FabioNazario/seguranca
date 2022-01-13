package br.edu.infnet.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.model.Perfil;
import br.edu.infnet.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>   {
	Optional<User> findByUsername(String username);
}
