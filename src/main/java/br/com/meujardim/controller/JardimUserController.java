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

//    @GetMapping("/{userId}")
//    ResponseEntity<JardimUser> procurarUserPorId(@PathVariable long userId) {
//        return ResponseEntity.ok(userService.encontrarUserPorId(userId));
//    }

//    @GetMapping("/{identificacao}")
//    ResponseEntity<JardimUser> recuperaDadosUser(@PathVariable String identificacao, LoginDTO dadosLogin) {
//     return ResponseEntity.ok(userService.recuperaDados(dadosLogin, identificacao));
//    }

    @PostMapping
    ResponseEntity<JardimUser> cadastrarUser(@RequestBody @Valid JardimUser novoUser) {
        return new ResponseEntity<>(userService.cadastrarUser(novoUser), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<JardimUser> validaLogin(@RequestBody @Valid LoginDTO dadosLogin) {
        return new ResponseEntity<>(userService.validaLogin(dadosLogin), HttpStatus.OK);
    }
}
