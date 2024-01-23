package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.entity.eUser;
import com.gerenciador.tarefas.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserAuthenticatedService implements UserDetailsService {

    @Autowired
    private IUserRepository iUserRepository;

    public UserDetails loadUserByUsername(String username){

        eUser eUser = iUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário " + username + "não foi encontrado"));

        List<SimpleGrantedAuthority> roles = eUser.getERoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new User(eUser.getUsername(), eUser.getPassword(), roles);
    }
}
