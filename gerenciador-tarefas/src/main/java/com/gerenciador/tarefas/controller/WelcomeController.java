package com.gerenciador.tarefas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome-api")
    public String welcomeApi() {
        return "Sucesso! Bem-vindo(a) a página default da API!";
    }

    @GetMapping("/welcome-user")
    public String welcomeUser(@RequestParam(name = "nome") String nome) {
        return "Olá " + nome + ", seja muito bem-vindo(a)!";
    }
}
