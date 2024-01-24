package com.gerenciador.tarefas.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(){
        super();
    }

    public TaskNotFoundException(String mensagem){
        super(mensagem);
    }
}
