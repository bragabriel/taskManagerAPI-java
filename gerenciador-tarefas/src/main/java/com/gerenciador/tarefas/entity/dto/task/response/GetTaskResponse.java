package com.gerenciador.tarefas.entity.dto.task.response;

import com.gerenciador.tarefas.status.TaskStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class GetTaskResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private TaskStatusEnum status;
    private String responsavel;
    private String criador;
    private int quantidadeHorasEstimadas;
    private Integer quantidadeHorasRealizada;
    private LocalTime dataCadasto;
    private LocalTime dataAtualizacao;
}
