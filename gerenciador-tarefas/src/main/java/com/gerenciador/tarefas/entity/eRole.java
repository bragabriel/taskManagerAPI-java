package com.gerenciador.tarefas.entity;

import com.gerenciador.tarefas.permission.PermissionEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="roles")
@Data
@Getter
@Setter
public class eRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private PermissionEnum name;

    @ManyToMany(mappedBy = "roles")
    private List<eUser> eUsers;
}
