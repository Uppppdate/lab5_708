package org.example.data;

import java.security.SecureRandom;
import java.util.ArrayList;

public class IdManager {
    public static ArrayList<Integer> idList;

    public IdManager() {
        idList = new ArrayList<>();
    }

    public static Long generateId(){
        SecureRandom secureRandom = new SecureRandom();
        int id = secureRandom.nextInt(100000);
        while (idList.contains(id)){
            id = secureRandom.nextInt();
        }
        idList.add(id);
        return (long) id;
    }

    public static boolean removeId(int id){
        if (idList.contains(id)) {
            idList.remove(id);
            return true;
        }
        else return false;
    }
}
