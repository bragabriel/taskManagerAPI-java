package com.gerenciador.tarefas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="users")
@Data
@Getter
@Setter
public class eUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true, length = 50)
    private String username;
    @Column(length = 150)
    private String password;

    //Relacionamento N-N
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( //Tipo de junção das tabelas
            name="users_roles",
            joinColumns = @JoinColumn(name="usuario_id"), //Junção das colunas será pelo usuario_id (tabela user)
            inverseJoinColumns = @JoinColumn(name="role_id"), //Junção das colunas será também pela role_id (tabela role)
            uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "role_id"} //Chave unica de acordo com o usuário e role id
    ))
    private List<eRole> roles;
}
