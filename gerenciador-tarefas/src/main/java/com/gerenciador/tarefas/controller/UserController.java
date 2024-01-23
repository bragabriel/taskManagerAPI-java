package com.gerenciador.tarefas.controller;

import com.gerenciador.tarefas.entity.eUser;
import com.gerenciador.tarefas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody eUser eUser){
        eUser savedUser = userService.saveUser(eUser);
        return new ResponseEntity<>("Novo usuário criado: " + savedUser.getUsername(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody eUser eUser){
        eUser updatedUser = userService.updateUser(eUser);
        return new ResponseEntity<>("Usuário atualizado com sucesso: " + updatedUser.getUsername(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<eUser>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody eUser eUser){
        userService.deleteUser(eUser);
    }
}
