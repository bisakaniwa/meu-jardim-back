package br.com.meujardim.DTOs;

import br.com.meujardim.model.JardimUser;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginDTO {

    @NotBlank
    private String identificacao;

    @NotBlank
    private String senha;

    public LoginDTO(JardimUser user) {
        this.senha = user.getSenha();
    }

    public LoginDTO() {}
}
