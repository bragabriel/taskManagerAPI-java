package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.entity.eTask;
import com.gerenciador.tarefas.exception.NotAllowedChangeStatus;
import com.gerenciador.tarefas.exception.NotAllowedDeleteTask;
import com.gerenciador.tarefas.exception.TaskNotFoundException;
import com.gerenciador.tarefas.repository.ITaskRepository;
import com.gerenciador.tarefas.entity.dto.task.request.CreateTaskRequest;
import com.gerenciador.tarefas.entity.dto.task.request.UpdateTaskRequest;
import com.gerenciador.tarefas.status.TaskStatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TaskService {

    @Autowired
    private ITaskRepository iTaskRepository;

    @Autowired
    private UserService userService;

    public eTask saveTask(CreateTaskRequest request){
        eTask tarefaValidacao = iTaskRepository.findByTituloOrDescricao(request.getTitulo(), request.getDescricao());

        if (tarefaValidacao != null) {
            throw new TaskNotFoundException("Já existe uma tarefa com o mesmo titulo ou descrição");
        }

        eTask tarefa = eTask.builder()
                .quantidadeHorasEstimadas(request.getQuantidadeHorasEstimadas())
                .status(TaskStatusEnum.CRIADA)
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .criador(userService.obterUsuarioId(request.getCriadorId()).get())
                .build();


        return this.iTaskRepository.save(tarefa);
    }

    public Page<eTask> getTaskByTitle(String titulo, Pageable pegeable){
        return this.iTaskRepository.findByTituloContainingOrderByDataAtualizacaoDesc(titulo, pegeable);
    }

    public Page<eTask> getAllTasks(Pageable pegeable){

        return this.iTaskRepository.findAllByOrderByDataAtualizacaoDesc(pegeable);
    }

    public eTask updateTask(Long id, UpdateTaskRequest request){

        eTask tarefa = this.iTaskRepository.findById(id).get();

        if (tarefa.getStatus().equals(TaskStatusEnum.FINALIZADA)) {
            throw new NotAllowedChangeStatus("Não permitido mover tarefa que está FINALIZADA");
        }

        if (tarefa.getStatus().equals(TaskStatusEnum.CRIADA) && request.getStatus().equals(TaskStatusEnum.FINALIZADA)) {
            throw new NotAllowedChangeStatus("Não permitido mover a tarefa para FINALIZADA se a mesma estiver com status de CRIADA");
        }

        if (tarefa.getStatus().equals(TaskStatusEnum.BLOQUEADA) && request.getStatus().equals(TaskStatusEnum.FINALIZADA)) {
            throw new NotAllowedChangeStatus("Não permitido mover a tarefa para FINALIZADA se a mesma estiver com status de BLOQUEADA");
        }

        tarefa.setQuantidadeHorasEstimadas(request.getQuantidadeHorasEstimadas());
        tarefa.setStatus(request.getStatus());
        tarefa.setTitulo(request.getTitulo());
        tarefa.setDescricao(request.getDescricao());
        tarefa.setResponsavel(userService.obterUsuarioId(request.getResponsavelId()).get());
        tarefa.setQuantidadeHorasRealizada(request.getQuantidadeHorasRealizada());

        this.iTaskRepository.save(tarefa);

        return tarefa;
    }

    public void deleteTask(Long id){

        eTask tarefa = this.iTaskRepository.findById(id).get();

        if (!TaskStatusEnum.CRIADA.equals(tarefa.getStatus())) {
            throw new NotAllowedDeleteTask();
        }

        this.iTaskRepository.deleteById(id);
    }
}
