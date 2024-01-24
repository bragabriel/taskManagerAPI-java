package com.gerenciador.tarefas.repository;

import com.gerenciador.tarefas.entity.eTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskRepository extends JpaRepository<eTask, Long> {

    eTask findByTituloOrDescricao(String titulo, String descricao);
    Page<eTask> findByTituloContainingOrderByDataAtualizacaoDesc(String titulo, Pageable pegeable);
    Page<eTask> findAllByOrderByDataAtualizacaoDesc(Pageable pegeable);
}

