package com.gerenciador.tarefas.exception;

public class NotAllowedChangeStatus extends RuntimeException {

    public NotAllowedChangeStatus(){
        super();
    }

    public NotAllowedChangeStatus(String mensagem) {
        super(mensagem);
    }
}