package com.gerenciador.tarefas.repository;

import com.gerenciador.tarefas.entity.eUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<eUser, Long> {

    Optional<eUser> findByUsername(String username);
}
