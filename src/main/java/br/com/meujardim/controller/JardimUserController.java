package br.com.meujardim.controller;

import br.com.meujardim.DTOs.LoginDTO;
import br.com.meujardim.model.JardimUser;
import br.com.meujardim.service.JardimUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class JardimUserController {
    private final JardimUserService userService;

    public JardimUserController(JardimUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity<JardimUser> cadastrarUser(@RequestBody @Valid JardimUser novoUser) {
        return new ResponseEntity<>(userService.cadastrarUser(novoUser), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<JardimUser> validaLogin(@RequestBody @Valid LoginDTO dadosLogin) {
        return new ResponseEntity<>(userService.validaLogin(dadosLogin), HttpStatus.OK);
    }

    @PutMapping("/atualizar")
    ResponseEntity<JardimUser> atualizarUser(@RequestBody @Valid JardimUser userAtualizado) {
        return new ResponseEntity<>(userService.atualizaUser(userAtualizado), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<JardimUser> excluirConta(@PathVariable long userId) {
        userService.excluirConta(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
