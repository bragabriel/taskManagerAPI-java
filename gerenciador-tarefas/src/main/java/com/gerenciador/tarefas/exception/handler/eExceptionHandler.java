package com.gerenciador.tarefas.exception.handler;

import com.gerenciador.tarefas.exception.NotAllowedChangeStatus;
import com.gerenciador.tarefas.exception.NotAllowedDeleteTask;
import com.gerenciador.tarefas.exception.TaskNotFoundException;
import com.gerenciador.tarefas.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class eExceptionHandler {

        @ExceptionHandler(NotAllowedDeleteTask.class)
        public ResponseEntity<ErrorResponse> naoPermitirExcluirExceptionHandler(NotAllowedDeleteTask ex) {

            Map<String, String> response = new HashMap<>();
            response.put("codigo", ErrorEnum.NAO_PERMITIDO_EXCLUIR.toString());
            response.put("mensagem", "Não é permitido excluir uma tarefa com o status diferente de CRIADA");

            ErrorResponse errorResponse =  ErrorResponse
                    .builder()
                    .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                    .errors(Collections.singletonList(response))
                    .build();

            return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        @ExceptionHandler(NotAllowedChangeStatus.class)
        public ResponseEntity<ErrorResponse> naoPermitoAlterarStatusExceptionHandler(NotAllowedChangeStatus ex) {

            Map<String, String> response = new HashMap<>();
            response.put("codigo", ErrorEnum.NAO_PERMITIDO_EXCLUIR.toString());
            response.put("mensagem", ex.getMessage());

            ErrorResponse errorResponse =  ErrorResponse
                    .builder()
                    .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                    .errors(Collections.singletonList(response))
                    .build();

            return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        @ExceptionHandler(TaskNotFoundException.class)
        public ResponseEntity<ErrorResponse> tarefaExistenteExceptionHandler(TaskNotFoundException ex) {

            Map<String, String> response = new HashMap<>();
            response.put("codigo", ErrorEnum.NAO_PERMITIDO_EXCLUIR.toString());
            response.put("mensagem", ex.getMessage());

            ErrorResponse errorResponse =  ErrorResponse
                    .builder()
                    .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                    .errors(Collections.singletonList(response))
                    .build();

            return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
        }
}
