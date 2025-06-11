package org.example.managers;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.data.Validator;
import org.example.files.DataErrorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AuthorizationManager {
    public static long currentUserId;
    public static String currentUserName;
    public static boolean isLogged;

    public void toRegister() throws DataErrorException, AuthorizationException {
        String name;
        String password1;
        String password2;
        Scanner scanner = new Scanner(System.in);
        String dataInLine;
        try {
            System.out.println("Введите команду в формате:");
            System.out.println("Логин пароль пароль");
            dataInLine = scanner.nextLine();
            String[] data = dataInLine.split(" ");
            name = data[0];
            password1 = data[1];
            password2 = data[2];
        } catch (IndexOutOfBoundsException e) {
            throw new DataErrorException("Введите пароль дважды");
        }
        if (password1.equals(password2)) {
            String hash_password = DigestUtils.sha512_224Hex(password2);
            String query = "INSERT INTO s465521.\"users\" (username, password_hash) VALUES ('" + name + "', '" + hash_password + "');";
            try {
                ConnectionManager.execute(query);
                try (Connection conn = ConnectionManager.getConnection();
                     PreparedStatement ps = conn.prepareStatement(
                             "SELECT id FROM s465521.users WHERE username = ? AND password_hash = ?")) {

                    ps.setString(1, name);
                    ps.setString(2, hash_password);

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) currentUserId = rs.getLong("id");
                }
                isLogged = true;
                currentUserName = name;
                System.out.println("Новый пользователь успешно авторизован");
            } catch (SQLException e) {
                throw new AuthorizationException("Такое имя пользователя уже есть");
            }
        } else {
            throw new AuthorizationException("Введённые пароли не совпадают");
        }

    }

    public void toAuthorize() throws AuthorizationException {
        Scanner scanner = new Scanner(System.in);
        String username;
        try {
            System.out.println("Введите логин");
            username = scanner.nextLine().split(" ")[0];
            Validator.checkName(username);
            if (!checkUserName(username)) {
                throw new AuthorizationException("Неверный логин");
            }
        } catch (DataErrorException e) {
            throw new AuthorizationException("Неверный логин");
        }
        String password;
        try {
            System.out.println("Введите пароль");
            password = scanner.nextLine();
            Validator.checkName(password);
            if (!checkPassword(username, password)) {
                throw new AuthorizationException("Неверный пароль");
            }
            System.out.println("Вы успешно вошли в аккаунт: " + username);
            try (Connection conn = ConnectionManager.getConnection();
                 PreparedStatement ps = conn.prepareStatement(
                         "SELECT id FROM s465521.users WHERE username = ? AND password_hash = ?")) {

                ps.setString(1, username);
                ps.setString(2, DigestUtils.sha512_224Hex(password));

                ResultSet rs = ps.executeQuery();
                if (rs.next()) currentUserId = rs.getLong("id");
                currentUserName = username;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (DataErrorException e) {
            throw new AuthorizationException("Вспоминайте пароль!");
        }
    }

    private static boolean checkUserName(String userNameToCheck) {
        String query = "select exists (select 1 from s465521.\"users\" where username =? )";
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userNameToCheck);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean checkPassword(String username, String password) {
        String query = "select password_hash from s465521.\"users\" where username = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String passwordToCheck = rs.getString("password_hash");
                //костыль, поскольку таблица создана с фиксированным размером пароля больше чем надо и остаток заполнен пробелами(
                passwordToCheck = passwordToCheck.split(" ")[0];
                String hashedPassword = hashPassword(password);
                return hashedPassword.equals(passwordToCheck);
            }
        } catch (SQLException e) {
            System.out.println("Error by checking password...");
        }

        return false;
    }

    private static String hashPassword(String password) {
        return DigestUtils.sha512_224Hex(password);
    }


}
