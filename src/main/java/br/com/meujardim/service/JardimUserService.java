package br.com.meujardim.service;

import br.com.meujardim.DTOs.LoginDTO;
import br.com.meujardim.exception.UniqueUseException;
import br.com.meujardim.exception.UserNotFoundException;
import br.com.meujardim.model.JardimUser;
import br.com.meujardim.repository.JardimUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNullElse;

@Service
public class JardimUserService {
    private final JardimUserRepository userRepository;

    public JardimUserService(JardimUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public JardimUser buscarPorId(long userId) {
        Optional<JardimUser> busca = userRepository.findById(userId);
        if (busca.isEmpty()) {
            throw new UserNotFoundException("Usuário não encontrado.");
        } else {
            return busca.get();
        }
    }

    @Transactional
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

    public boolean verificaSerAdmin(long userId) {
        Optional<JardimUser> procuraUser = userRepository.findById(userId);
        if (procuraUser.isEmpty()) {
            throw new UserNotFoundException("Usuário não encontrado.");
        } else return procuraUser.get().getIsAdmin();
    }

    @Transactional
    public JardimUser atualizaUser(JardimUser userAtualizado) {
        Optional<JardimUser> userBuscado = userRepository.findById(userAtualizado.getUserId());
        if (userBuscado.isEmpty()) {
            throw new UserNotFoundException("Usuário não encontrado.");
        } else {
            JardimUser user = userBuscado.get();
            user.setPrimeiroNome(requireNonNullElse(userAtualizado.getPrimeiroNome(), user.getPrimeiroNome()));
            user.setUltimoNome(requireNonNullElse(userAtualizado.getUltimoNome(), user.getUltimoNome()));
            user.setUsername(requireNonNullElse(userAtualizado.getUsername(), user.getUsername()));
            user.setEmail(requireNonNullElse(userAtualizado.getEmail(), user.getEmail()));
            user.setSenha(requireNonNullElse(userAtualizado.getSenha(), user.getSenha()));
            return userRepository.save(user);
        }
    }

    @Transactional
    public void excluirConta(long userId) {
        Optional<JardimUser> userParaExcluir = userRepository.findById(userId);
        if (userParaExcluir.isEmpty()) {
            throw new UserNotFoundException("Usuário não encontrado.");
        } else {
            userRepository.deleteById(userId);
        }
    }
}
