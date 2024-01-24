package com.gerenciador.tarefas.repository;

import com.gerenciador.tarefas.entity.eRole;
import com.gerenciador.tarefas.permission.PermissionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<eRole, Long> {

    eRole findByName(PermissionEnum name);
}
