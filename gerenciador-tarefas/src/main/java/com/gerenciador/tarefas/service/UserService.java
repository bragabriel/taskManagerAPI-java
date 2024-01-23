package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.entity.User;
import com.gerenciador.tarefas.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepository iUserRepository;

    public User saveUser(User user){
        return this.iUserRepository.save(user);
    }

    public User updateUser(User user){
        return this.iUserRepository.save(user);
    }

    public void deleteUser(User user){
        this.iUserRepository.deleteById(user.getId());
    }

    public List<User> getAllUsers(){
        return this.iUserRepository.findAll();
    }
}
