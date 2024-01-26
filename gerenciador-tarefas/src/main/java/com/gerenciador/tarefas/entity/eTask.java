package com.gerenciador.tarefas.entity;

import com.gerenciador.tarefas.status.TaskStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name="tasks")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class eTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatusEnum status;

    @Column
    private eUser responsavel;

    @Column(nullable = false)
    private eUser criador;

    @Column(nullable = false)
    private int quantidadeHorasEstimadas;

    @Column
    private Integer quantidadeHorasRealizada;

    @Column
    @CreationTimestamp
    private LocalTime dataCadasto;

    @Column
    @UpdateTimestamp
    private LocalTime dataAtualizacao;

}
