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
    public ResponseEntity<eUser> saveUser(@RequestBody eUser eUser){
        return new ResponseEntity<>(userService.saveUser(eUser), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<eUser> updateUser(@RequestBody eUser eUser){
        return new ResponseEntity<>(userService.updateUser(eUser), HttpStatus.OK);
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
