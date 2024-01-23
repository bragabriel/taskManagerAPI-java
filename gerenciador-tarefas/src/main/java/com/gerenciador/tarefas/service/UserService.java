package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.entity.eUser;
import com.gerenciador.tarefas.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public eUser saveUser(eUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.iUserRepository.save(user);
    }

    public eUser updateUser(eUser user){
        return this.iUserRepository.save(user);
    }

    public void deleteUser(eUser user){
        this.iUserRepository.deleteById(user.getId());
    }

    public List<eUser> getAllUsers(){
        return this.iUserRepository.findAll();
    }
}
