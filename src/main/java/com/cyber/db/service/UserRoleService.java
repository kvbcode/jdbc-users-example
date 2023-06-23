package com.cyber.db.service;

import com.cyber.db.entity.Role;
import com.cyber.db.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRoleService {
    private static final String QUERY_USERS_BY_ROLE = """
            select u.id, u.name
            from users u
            left join users_roles ur on ur.user_id = u.id
            left join roles r on ur.role_nameid = r.nameid
            where r.nameid=upper(?1);
            """;

    private static final String QUERY_USER_ROLES_BY_USER_ID = """
            select ur.role_nameid
            from users_roles ur
            where ur.user_id = ?1;
            """;

    private static final String QUERY_USER_ROLES_BY_USER_NAME = """
            select r.nameid
            from users_roles ur
            join users u on ur.user_id = u.id
            join roles r on ur.role_nameid = r.nameid
            where u.name = ?1;
            """;

    private final Connection connection;


    public UserRoleService(Connection connection) {
        this.connection = connection;
    }

    public List<User> findUsersByRole(String role) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_USERS_BY_ROLE)) {
            preparedStatement.setString(1, role);

            ArrayList<User> namesList = new ArrayList<>();

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                namesList.add(new User(id, name));
            }
            return namesList;
        }
    }

    public Set<Role> findUserRolesByUserId(long userId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_USER_ROLES_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);

            HashSet<Role> roles = new HashSet<>();

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nameId = resultSet.getString(1);
                roles.add(new Role(nameId));
            }
            return roles;
        }
    }

    public Set<Role> findUserRolesByUserName(String userName) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_USER_ROLES_BY_USER_NAME)) {
            preparedStatement.setString(1, userName);

            HashSet<Role> roles = new HashSet<>();

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nameId = resultSet.getString(1);
                roles.add(new Role(nameId));
            }
            return roles;
        }
    }
}
