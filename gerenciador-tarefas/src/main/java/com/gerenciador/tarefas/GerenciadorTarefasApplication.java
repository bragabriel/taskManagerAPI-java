package com.gerenciador.tarefas;

import com.gerenciador.tarefas.entity.eRole;
import com.gerenciador.tarefas.entity.eUser;
import com.gerenciador.tarefas.permission.PermissionEnum;
import com.gerenciador.tarefas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GerenciadorTarefasApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorTarefasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		eUser user = new eUser();
		user.setUsername("admin");
		user.setPassword("123");

		List<eRole> roles = new ArrayList<>();
		eRole roleAdmin = new eRole();
		roleAdmin.setName(PermissionEnum.ADMIN);
		eRole roleUser = new eRole();
		roleUser.setName(PermissionEnum.USER);

		roles.add(roleAdmin);
		roles.add(roleUser);
		user.setRoles(roles);

		userService.saveUser(user);
	}
}
