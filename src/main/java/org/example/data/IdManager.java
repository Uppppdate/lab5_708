package org.example.data;

import java.security.SecureRandom;
import java.util.ArrayList;

public class IdManager {
    public static ArrayList<Long> idList = new ArrayList<>();
    public static final Long MIN = 0L;
    public static final Long MAX = 100000L;

    public static boolean checkId(Long id){
        return idList.contains(id);
    }
    public static Long generateId() {
        SecureRandom secureRandom = new SecureRandom();
        Long id = secureRandom.nextLong(MAX);
        while (idList.contains(id)) {
            id = secureRandom.nextLong(MAX);
        }
        idList.add(id);
        return id;
    }

    public static boolean removeId(Long id) {
        if (idList.contains(id)) {
            idList.remove(id);
            return true;
        } else return false;
    }
}
