package org.example.data;

import java.security.SecureRandom;
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
     * @param id
     * @return
     */
    public static boolean checkId(Long id){
        return idList.contains(id);
    }

    /**
     * Генерирует ID и добавляет в список id
     * @see IdManager#idList
     * @return Сгенерированное новое ID
     */
    public static Long generateId() {
        SecureRandom secureRandom = new SecureRandom();
        Long id = secureRandom.nextLong(MAX);
        while (idList.contains(id)) {
            id = secureRandom.nextLong(MAX);
        }
        idList.add(id);
        return id;
    }

    /**
     * Генерирует ID, но не добавляет в список
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

    /**
     * Добавляет ID в список ID
     * @param id
     * @return
     */
    public static boolean addId(Long id){
        if (!idList.contains(id)) {
            idList.add(id);
            return true;
        } else return false;
    }

    /**
     * Удаляет ID из списка ID
     * @param id
     * @return
     */
    public static boolean removeId(Long id) {
        if (idList.contains(id)) {
            idList.remove(id);
            return true;
        } else return false;
    }
}
