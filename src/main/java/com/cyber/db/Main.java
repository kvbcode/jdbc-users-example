package com.cyber.db;

import com.cyber.db.entity.Role;
import com.cyber.db.entity.User;
import com.cyber.db.service.UserRoleService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        String url = "jdbc:h2:mem:temp";
        String username = "sa";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            executeScript(connection, Path.of("db_init.sql"));
            UserRoleService app = new UserRoleService(connection);

            List<User> users = app.findUsersByRole("read");
            System.out.println("Пользователи с ролью 'READ': ");
            users.forEach(System.out::println);

            Set<Role> roleSetUser1 = app.findUserRolesByUserId(1L);
            System.out.println("Роли пользователя id=1: " + roleSetUser1);

            Set<Role> roleSetUser3 = app.findUserRolesByUserId(3L);
            System.out.println("Роли пользователя id=3: " + roleSetUser3);

            Set<Role> roleSetUser6 = app.findUserRolesByUserId(6L);
            System.out.println("Роли пользователя id=6: " + roleSetUser6);

            Set<Role> roleSetUserPierre = app.findUserRolesByUserName("Pierre J. Gouge");
            System.out.println("Роли пользователя 'Pierre J. Gouge': " + roleSetUserPierre);
        }
    }

    public static void executeScript(Connection connection, Path scriptPath) throws SQLException, IOException {
        String sql = Files.readString(scriptPath);
        Statement statement = connection.createStatement();
        statement.executeLargeUpdate(sql);
    }

}
