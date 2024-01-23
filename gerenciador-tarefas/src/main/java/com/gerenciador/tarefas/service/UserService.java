package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.entity.eUser;
import com.gerenciador.tarefas.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepository iUserRepository;

    public eUser saveUser(eUser eUser){
        return this.iUserRepository.save(eUser);
    }

    public eUser updateUser(eUser eUser){
        return this.iUserRepository.save(eUser);
    }

    public void deleteUser(eUser eUser){
        this.iUserRepository.deleteById(eUser.getId());
    }

    public List<eUser> getAllUsers(){
        return this.iUserRepository.findAll();
    }
}
