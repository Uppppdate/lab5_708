package org.example.data;

import org.example.files.DataErrorException;
import org.example.files.DataParser;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.util.Date;

/**
 * Класс для проверки значение
 */
public class Validator {

    /**
     * Проверяет объект на null
     *
     * @param obj объект для проверки
     * @return
     * @throws NullPointerException пробрасывается если объект null
     */
    public static boolean isNull(Object obj) throws NullPointerException {
        if (obj == null) {
            throw new NullPointerException();
        }
        return false;
    }

    /**
     * Проверяет строку на null, незаполненность
     *
     * @param input
     * @return
     */
    public static boolean isNull(String input) {
        isNull((Object) input);
        if (input.isEmpty() || input.equals("null")) {
            throw new NullPointerException();
        } else return true;
    }

    /**
     * Проверяет id на нахождение в нужном диапазоне, а также на его ОТСУТСТВИЕ в коллекции
     *
     * @param id
     * @return
     * @throws DataErrorException
     */
    public static boolean checkId(String id) throws DataErrorException {
        try {
            Long parsed_id = Long.valueOf(id);
            //Проверяет если значение не превышает MAX, не меньше MIN и НЕ СОДЕРЖИТСЯ в коллекции, то все хорошо
            if (!(parsed_id > IdManager.MIN & parsed_id < IdManager.MAX & !IdManager.checkId(parsed_id))) {
                throw new DataErrorException("Неверное ID");
            }
        } catch (NumberFormatException e) {
            throw new DataErrorException("Неверное ID");
        }
        return true;
    }

    /**
     * Проверяет строку на тип Long
     *
     * @param longValue
     * @return
     * @throws DataErrorException
     */
    public static boolean checkLong(String longValue) throws DataErrorException {
        try {
            Long.valueOf(longValue);
        } catch (NumberFormatException e) {
            throw new DataErrorException("Данные в неправильном формате");
        }
        return true;
    }

    /**
     * Проверяет name
     *
     * @param name
     * @return
     * @throws DataErrorException
     * @see Organization#name
     */
    public static boolean checkName(String name) throws DataErrorException {
        try {
            isNull(name);
        } catch (NullPointerException e) {
            throw new DataErrorException("Неверное имя");
        }
        return true;
    }

    /**
     * Проверяет X из класса Coordinates
     *
     * @param x
     * @return
     * @throws DataErrorException
     * @see Coordinates
     */
    public static boolean checkCoordinatesX(String x) throws DataErrorException {
        try {
            isNull(x);
            Long.parseLong(x);
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Неверная X координата");
        }
        return true;
    }

    /**
     * Проверяет y из класса Coordinates
     *
     * @param y
     * @return
     * @throws DataErrorException
     */
    public static boolean checkCoordinatesY(String y) throws DataErrorException {
        try {
            isNull(y);
            double new_y = Double.parseDouble(y);
            if (new_y > 482) throw new DataErrorException("Неверная Y координата");
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Неверная Y координата");
        }
        return true;
    }

    /**
     * Проверяет дату
     *
     * @param date
     * @return
     * @throws DataErrorException
     * @see Organization#creationDate
     */
    public static boolean checkDate(String date) throws DataErrorException {
        if (date.equals("current")) {
            return true;
        }
        try {
            DataParser.formatter.parse(date);
        } catch (ParseException e) {
            throw new DataErrorException("Неверная дата");
        }
        return true;
    }

    /**
     * Проверяет annualTurnover
     *
     * @param annualTurnover
     * @return
     * @throws DataErrorException
     */
    public static boolean checkAnnualTurnover(String annualTurnover) throws DataErrorException {
        try {
            isNull(annualTurnover);
            float new_annualTurnover = Float.parseFloat(annualTurnover);
            if (new_annualTurnover <= 0) throw new DataErrorException("Неверный годовой оборот");
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Неверный годовой оборот");
        }
        return true;
    }

    /**
     * Проверяет employeesCount
     *
     * @param employeesCount
     * @return
     * @throws DataErrorException
     */
    public static boolean checkEmployeesCount(String employeesCount) throws DataErrorException {
        try {
            isNull(employeesCount);
            Integer new_employeesCount = Integer.parseInt(employeesCount);
            if (new_employeesCount <= 0) throw new DataErrorException("Неверное число работников");
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Неверное число работников");
        }
        return true;
    }

    /**
     * Проверяет organizationType
     *
     * @param organizationType
     * @return
     * @throws DataErrorException
     */
    public static boolean checkOrganizationType(String organizationType) throws DataErrorException {
        try {
            isNull(organizationType);
            OrganizationType type = OrganizationType.valueOf(organizationType.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new DataErrorException("Неверный тип организации");
        }
        return true;
    }

    /**
     * Проверяет street
     *
     * @param street
     * @return
     * @throws DataErrorException
     */
    public static boolean checkStreet(String street) throws DataErrorException {
        try {
            if (street != null) {
                if (street.length() > 85) throw new DataErrorException("Неверная улица");
            }
        } catch (NullPointerException e) {
            throw new DataErrorException("Неверная улица");
        }
        return true;
    }

    /**
     * Проверяет zipCode
     *
     * @param zipCode
     * @return
     * @throws DataErrorException
     */
    public static boolean checkZipCode(String zipCode) throws DataErrorException {
        try {
            isNull(zipCode);
        } catch (NullPointerException e) {
            throw new DataErrorException("Неверный индекс");
        }
        return true;
    }

    /**
     * Проверяет X из класса Location
     *
     * @param x
     * @return
     * @throws DataErrorException
     * @see Location
     */
    public static boolean checkLocationX(String x) throws DataErrorException {
        try {
            isNull(x);
            double new_x = Double.parseDouble(x);
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Неправильная X координата локации");
        }
        return true;
    }

    /**
     * Проверяет Y из класса Location
     *
     * @param y
     * @return
     * @throws DataErrorException
     * @see Location
     */
    public static boolean checkLocationY(String y) throws DataErrorException {
        try {
            isNull(y);
            double new_y = Double.parseDouble(y);
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Неправильная Y координата локации");
        }
        return true;
    }

    /**
     * Проверяет Z из класса Location
     *
     * @param z
     * @return
     * @throws DataErrorException
     * @see Location
     */
    public static boolean checkLocationZ(String z) throws DataErrorException {
        try {
            isNull(z);
            Long new_z = Long.parseLong(z);
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Неправильная Z координата локации");
        }
        return true;
    }


    /**
     * Проверяет полные данные для объекта
     *
     * @param data
     * @throws DataErrorException
     */
    public static void checkData(String[] data) throws DataErrorException {
        try {
            checkId(data[0]);
            checkName(data[1]);
            checkCoordinatesX(data[2]);
            checkCoordinatesY(data[3]);
            checkDate(data[4]);
            checkAnnualTurnover(data[5]);
            checkEmployeesCount(data[6]);
            checkOrganizationType(data[7]);
            checkStreet(data[8]);
            checkZipCode(data[9]);
            checkLocationX(data[10]);
            checkLocationY(data[11]);
            checkLocationZ(data[12]);
        } catch (DataErrorException e) {
            throw new DataErrorException("Данные неверны:\n\t" + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new DataErrorException("Недостаточно данных");
        }
    }

    /**
     * Проверяет наличие аргументов в переданной строке команды, разбитой на токены
     *
     * @param args
     * @return
     * @throws DataErrorException
     */
    public static boolean checkArgs(String[] args) throws DataErrorException {
        if (args.length < 2 || args[1] == null || args[1].isEmpty()) {
            throw new DataErrorException("Аргументы не указаны или пустые");
        }
        return true;
    }
}
