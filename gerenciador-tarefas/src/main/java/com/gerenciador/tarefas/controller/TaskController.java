package com.gerenciador.tarefas.controller;

import com.gerenciador.tarefas.entity.eTask;
import com.gerenciador.tarefas.entity.dto.task.request.CreateTaskRequest;
import com.gerenciador.tarefas.entity.dto.task.request.UpdateTaskRequest;
import com.gerenciador.tarefas.entity.dto.task.response.CreateTaskResponse;
import com.gerenciador.tarefas.entity.dto.task.response.GetPaginatedTaskResponse;
import com.gerenciador.tarefas.entity.dto.task.response.GetTaskResponse;
import com.gerenciador.tarefas.entity.dto.task.response.UpdateTaskResponse;
import com.gerenciador.tarefas.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<CreateTaskResponse> saveTask(@Valid @RequestBody CreateTaskRequest request) {

        eTask tarefaSalva  = taskService.saveTask(request);

        CreateTaskResponse response = CreateTaskResponse
                .builder()
                .id(tarefaSalva.getId())
                .titulo(tarefaSalva.getTitulo())
                .descricao(tarefaSalva.getDescricao())
                .criador(tarefaSalva.getCriador().getUsername())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GetPaginatedTaskResponse> getAllTasksPaginated(
            @RequestParam(required = false) String titulo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ){

        Page<eTask> tarefasPaginada = null;

        if ( titulo == null ) {
            tarefasPaginada = this.taskService.getAllTasks(PageRequest.of(page, size));
        } else {
            tarefasPaginada = this.taskService.getTaskByTitle(titulo, PageRequest.of(page, size));
        }

        List<GetTaskResponse> tarefas = tarefasPaginada
                .getContent()
                .stream()
                .map(tarefa -> {
                    return GetTaskResponse
                            .builder()
                            .id(tarefa.getId())
                            .titulo(tarefa.getTitulo())
                            .descricao(tarefa.getDescricao())
                            .responsavel(tarefa.getResponsavel() != null ?tarefa.getResponsavel().getUsername() : "NAO_ATRIBUIDA")
                            .criador(tarefa.getCriador().getUsername())
                            .status(tarefa.getStatus())
                            .quantidadeHorasEstimadas(tarefa.getQuantidadeHorasEstimadas())
                            .quantidadeHorasRealizada(tarefa.getQuantidadeHorasRealizada())
                            .dataCadasto(tarefa.getDataCadasto())
                            .dataAtualizacao(tarefa.getDataAtualizacao())
                            .build();
                })
                .toList();

        GetPaginatedTaskResponse response = GetPaginatedTaskResponse
                .builder()
                .paginaAtual(tarefasPaginada.getNumber())
                .totalPaginas(tarefasPaginada.getTotalPages())
                .totalItens(tarefasPaginada.getTotalElements())
                .tarefas(tarefas)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UpdateTaskResponse> updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskRequest request) {

        eTask tarefaAtualizada = taskService.updateTask(id, request);

        UpdateTaskResponse response = UpdateTaskResponse
                .builder()
                .id(tarefaAtualizada.getId())
                .titulo(tarefaAtualizada.getTitulo())
                .descricao(tarefaAtualizada.getDescricao())
                .criador(tarefaAtualizada.getCriador().getUsername())
                .quantidadeHorasEstimadas(tarefaAtualizada.getQuantidadeHorasEstimadas())
                .status(tarefaAtualizada.getStatus().toString())
                .responsavel(tarefaAtualizada.getResponsavel().getUsername())
                .quantidadeHorasRealizada(tarefaAtualizada.getQuantidadeHorasRealizada() != null ? tarefaAtualizada.getQuantidadeHorasRealizada() : null)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}
