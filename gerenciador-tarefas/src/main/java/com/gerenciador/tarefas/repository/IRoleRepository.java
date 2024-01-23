package com.gerenciador.tarefas.repository;

import com.gerenciador.tarefas.entity.eRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<eRole, Long> {

    Optional<eRole> findByName(String username);
}
