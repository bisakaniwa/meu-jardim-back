package br.com.meujardim.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Getter
@NoArgsConstructor
public class JardimUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long userId;

    @NotBlank
    @Column(name = "primeiro_nome")
    private String primeiroNome;

    @Column(name = "ultimo_nome")
    private String ultimoNome;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @Value("0")
    @Column(name = "is_admin")
    private Boolean isAdmin;

    public JardimUser(long userId, String primeiroNome, String ultimoNome, String username, String email, String senha, Boolean isAdmin) {
        this.userId = userId;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.isAdmin = isAdmin;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
