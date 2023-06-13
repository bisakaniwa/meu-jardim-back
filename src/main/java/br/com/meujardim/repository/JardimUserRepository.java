package br.com.meujardim.repository;

import br.com.meujardim.model.JardimUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JardimUserRepository extends JpaRepository<JardimUser, Long> {
    JardimUser findByEmail(String email);
    JardimUser findByUsername(String username);
    boolean existsByUsernameOrEmail(String username, String email);
    JardimUser findByUsernameAndSenha(String username, String senha);
    JardimUser findByEmailAndSenha(String email, String senha);
}
