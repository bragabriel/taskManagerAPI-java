package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.entity.eRole;
import com.gerenciador.tarefas.entity.eUser;
import com.gerenciador.tarefas.repository.IRoleRepository;
import com.gerenciador.tarefas.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IRoleRepository iRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public eUser saveUser(eUser user){

        //Recuperando as roles existentes do banco
        List<eRole> userRoles = user.getRoles()
                            .stream()
                            .map(role -> iRoleRepository.findByName(role.getName())).toList();

        user.setRoles(userRoles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return this.iUserRepository.save(user);
    }

    public eUser updateUser(eUser user){

        //Recuperando as roles existentes do banco
        List<eRole> userRoles = user.getRoles()
                .stream()
                .map(role -> iRoleRepository.findByName(role.getName())).toList();

        user.setRoles(userRoles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return this.iUserRepository.save(user);
    }

    public void deleteUser(eUser user){
        this.iUserRepository.deleteById(user.getId());
    }

    public List<eUser> getAllUsers(){
        return this.iUserRepository.findAll();
    }

    public Optional<eUser> obterUsuarioId(Long ususarioId) {
        return this.iUserRepository.findById(ususarioId);
    }
}
