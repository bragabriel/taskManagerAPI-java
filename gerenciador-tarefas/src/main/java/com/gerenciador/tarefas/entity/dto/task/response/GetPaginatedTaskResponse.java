package com.gerenciador.tarefas.entity.dto.task.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class GetPaginatedTaskResponse {

    private int paginaAtual;
    private Long totalItens;
    private int totalPaginas;
    private List<GetTaskResponse> tarefas;
}
