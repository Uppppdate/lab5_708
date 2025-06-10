package org.example.data;

import org.example.managers.ConnectionManager;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Менеджер ID. Содержит в себе список использованных ID. Позволяет работать с ID.
 */
public class IdManager {
    /**
     * Список использованных ID
     */
    private static final ArrayList<Long> idList = new ArrayList<>();
    /**
     * Минимальное значение ID
     */
    public static final Long MIN = 0L;
    /**
     * Максимальное значение ID
     */
    public static final Long MAX = 100000L;

    /**
     * Проверяет наличие переданного ID в коллекции
     *
     * @param id id
     * @return true - содержится, false - не содержится
     */
    public static boolean checkId(Long id) {
        return idList.contains(id);
    }

    /**
     * Обновляет список ID организаций из базы данных
     * @throws SQLException при ошибках работы с БД
     */
    public static void update() throws SQLException {
        // Очищаем текущий список
        idList.clear();

        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id FROM s465521.organizations")) {

            // Заполняем список ID из результата запроса
            while (resultSet.next()) {
                idList.add(resultSet.getLong("id"));
            }
        }
    }

    /**
     * Генерирует ID, но не добавляет в список
     *
     * @return Сгенерированное новое ID
     */
    public static Long generateIdWithoutAdding() {
        SecureRandom secureRandom = new SecureRandom();
        Long id = secureRandom.nextLong(MAX);
        while (idList.contains(id)) {
            id = secureRandom.nextLong(MAX);
        }
        return id;
    }

}
