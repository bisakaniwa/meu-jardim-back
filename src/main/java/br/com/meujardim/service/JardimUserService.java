package br.com.meujardim.service;

import br.com.meujardim.DTOs.LoginDTO;
import br.com.meujardim.exception.UniqueUseException;
import br.com.meujardim.exception.UserNotFoundException;
import br.com.meujardim.model.JardimUser;
import br.com.meujardim.repository.JardimUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class JardimUserService {
    private final JardimUserRepository userRepository;

    public JardimUserService(JardimUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<JardimUser> listarTodosOsUsers() {
        return userRepository.findAll();
    }

    public JardimUser encontrarUserPorId(long userId) {
        Optional<JardimUser> procurarUser = userRepository.findById(userId);
        if (procurarUser.isEmpty()) {
            throw new UserNotFoundException("Usuário não encontrado");
        } else {
            return procurarUser.get();
        }
    }

    public JardimUser cadastrarUser(JardimUser user) {
        boolean userInvalido = userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (userInvalido) {
            throw new UniqueUseException("Username ou e-mail já cadastrado");
        } else {
            return userRepository.save(user);
        }
    }

    public JardimUser validaLogin(LoginDTO dadosLogin) {
        JardimUser validoPorEmail = userRepository.findByEmailAndSenha(dadosLogin.getIdentificacao(), dadosLogin.getSenha());
        JardimUser validoPorUsername = userRepository.findByUsernameAndSenha(dadosLogin.getIdentificacao(), dadosLogin.getSenha());

        if (nonNull(validoPorEmail)) {
            return validoPorEmail;
        } else if (nonNull(validoPorUsername)) {
            return validoPorUsername;
        } else {
            throw new UserNotFoundException("Usuário não encontrado.");
        }
    }

//    public JardimUser recuperaDados(LoginDTO dadosLogin, String identificacao) {
//        JardimUser encontradoPorEmail = userRepository.findByEmail(identificacao);
//        JardimUser encontradoPorUsername = userRepository.findByUsername(identificacao);
//
//        if (validaLogin(dadosLogin).equals("Email")) {
//            return encontradoPorEmail;
//        } else if (validaLogin(dadosLogin).equals("Username")) {
//            return encontradoPorUsername;
//        } else {
//            throw new UserNotFoundException("Usuário não encontrado");
//        }
//    }
}
