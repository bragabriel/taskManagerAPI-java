package com.gerenciador.tarefas.entity.dto.task.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateTaskResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private String criador;
}
