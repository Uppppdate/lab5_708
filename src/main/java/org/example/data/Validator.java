package org.example.data;

import org.example.files.DataErrorException;
import org.example.files.DataParser;

import java.text.ParseException;

/**
 * Класс для проверки значение
 */
public class Validator {

    /**
     * Проверяет объект на null
     *
     * @param obj объект для проверки
     * @throws NullPointerException пробрасывается если объект null
     */
    public static void isNull(Object obj) throws NullPointerException {
        if (obj == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Проверяет строку на null, незаполненность
     *
     * @param input строка с параметром для проверки
     */
    public static void isNull(String input) {
        isNull((Object) input);
        if (input.isEmpty() || input.equals("null")) {
            throw new NullPointerException();
        }
    }

    /**
     * Проверяет id на нахождение в нужном диапазоне, а также на его ОТСУТСТВИЕ в коллекции
     *
     * @param id строка с параметром для проверки
     * @throws DataErrorException пробрасывается при не прохождении проверки данными
     */
    public static void checkId(String id) throws DataErrorException {
        try {
            Long parsed_id = Long.valueOf(id);
            //Проверяет если значение не превышает MAX, не меньше MIN и НЕ СОДЕРЖИТСЯ в коллекции, то все хорошо
            if (!(parsed_id > IdManager.MIN & parsed_id < IdManager.MAX & !IdManager.checkId(parsed_id))) {
                throw new DataErrorException("Неверное ID");
            }
        } catch (NumberFormatException e) {
            throw new DataErrorException("Неверное ID");
        }
    }

    /**
     * Проверяет строку на тип Long
     *
     * @param longValue строка с параметром для проверки
     * @throws DataErrorException пробрасывается при не прохождении проверки данными
     */
    public static void checkLong(String longValue) throws DataErrorException {
        try {
            Long.valueOf(longValue);
        } catch (NumberFormatException e) {
            throw new DataErrorException("Данные в неправильном формате");
        }
    }

    /**
     * Проверяет name
     *
     * @param name строка с параметром для проверки
     * @throws DataErrorException пробрасывается при не прохождении проверки данными
     * @see Organization
     */
    public static void checkName(String name) throws DataErrorException {
        try {
            if (name.equals("\n")) {
                throw new NullPointerException();
            }
            isNull(name);
        } catch (NullPointerException e) {
            throw new DataErrorException("Неверное имя");
        }
    }

    /**
     * Проверяет X из класса Coordinates
     *
     * @param x строка с параметром для проверки
     * @throws DataErrorException пробрасывается при не прохождении проверки данными
     * @see Coordinates
     */
    public static void checkCoordinatesX(String x) throws DataErrorException {
        try {
            isNull(x);
            Long.parseLong(x);
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Неверная X координата");
        }
    }

    /**
     * Проверяет y из класса Coordinates
     *
     * @param y строка с параметром для проверки
     * @throws DataErrorException пробрасывается при не прохождении проверки данными
     */
    public static void checkCoordinatesY(String y) throws DataErrorException {
        try {
            isNull(y);
            double new_y = Double.parseDouble(y);
            if (new_y > 482) throw new DataErrorException("Неверная Y координата");
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Неверная Y координата");
        }
    }

    /**
     * Проверяет дату
     *
     * @param date строка с параметром для проверки
     * @throws DataErrorException пробрасывается при не прохождении проверки данными
     * @see Organization
     */
    public static void checkDate(String date) throws DataErrorException {
        if (date.equals("current")) {
            return;
        }
        // Проверка формата и допустимых диапазонов с помощью регулярного выражения
        String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) ([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$";
        if (!date.matches(regex)) {
            throw new DataErrorException("Неверная дата");
        }
        // Проверка фактической корректности даты
        try {
            DataParser.formatter.parse(date);
        } catch (ParseException e) {
            throw new DataErrorException("Неверная дата");
        }
    }

    /**
     * Проверяет annualTurnover
     *
     * @param annualTurnover строка с параметром для проверки
     * @throws DataErrorException пробрасывается при не прохождении проверки данными
     */
    public static void checkAnnualTurnover(String annualTurnover) throws DataErrorException {
        try {
            isNull(annualTurnover);
            float new_annualTurnover = Float.parseFloat(annualTurnover);
            if (new_annualTurnover <= 0) throw new DataErrorException("Неверный годовой оборот");
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Неверный годовой оборот");
        }
    }

    /**
     * Проверяет employeesCount
     *
     * @param employeesCount строка с параметром для проверки
     * @throws DataErrorException пробрасывается при не прохождении проверки данными
     */
    public static void checkEmployeesCount(String employeesCount) throws DataErrorException {
        try {
            isNull(employeesCount);
            int new_employeesCount = Integer.parseInt(employeesCount);
            if (new_employeesCount <= 0) throw new DataErrorException("Неверное число работников");
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Неверное число работников");
        }
    }

    /**
     * Проверяет organizationType
     *
     * @param organizationType строка с параметром для проверки
     * @throws DataErrorException пробрасывается при не прохождении проверки данными
     */
    public static void checkOrganizationType(String organizationType) throws DataErrorException {
        try {
            isNull(organizationType);
            OrganizationType.valueOf(organizationType.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new DataErrorException("Неверный тип организации");
        }
    }

    /**
     * Проверяет street
     *
     * @param street строка с параметром для проверки
     * @throws DataErrorException пробрасывается при не прохождении проверки данными
     */
    public static void checkStreet(String street) throws DataErrorException {
        try {
            if (street != null) {
                if (street.length() > 85) throw new DataErrorException("Неверная улица");
            }
        } catch (NullPointerException e) {
            throw new DataErrorException("Неверная улица");
        }
    }

    /**
     * Проверяет zipCode
     *
     * @param zipCode строка с параметром для проверки
     * @throws DataErrorException пробрасывается при не прохождении проверки данными
     */
    public static void checkZipCode(String zipCode) throws DataErrorException {
        try {
            isNull(zipCode);
        } catch (NullPointerException e) {
            throw new DataErrorException("Неверный индекс");
        }
    }

    /**
     * Проверяет X из класса Location
     *
     * @param x строка с параметром для проверки
     * @throws DataErrorException пробрасывается при не прохождении проверки данными
     * @see Location
     */
    public static void checkLocationX(String x) throws DataErrorException {
        try {
            isNull(x);
            Double.parseDouble(x);
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Неправильная X координата локации");
        }
    }

    /**
     * Проверяет Y из класса Location
     *
     * @param y строка с параметром для проверки
     * @throws DataErrorException пробрасывается при не прохождении проверки данными
     * @see Location
     */
    public static void checkLocationY(String y) throws DataErrorException {
        try {
            isNull(y);
            Double.parseDouble(y);
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Неправильная Y координата локации");
        }
    }

    /**
     * Проверяет Z из класса Location
     *
     * @param z строка с параметром для проверки
     * @throws DataErrorException пробрасывается при не прохождении проверки данными
     * @see Location
     */
    public static void checkLocationZ(String z) throws DataErrorException {
        try {
            isNull(z);
            Long.parseLong(z);
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Неправильная Z координата локации");
        }
    }


    /**
     * Проверяет полные данные для объекта
     *
     * @param data массив с данными объёкта
     * @throws DataErrorException любая ошибка с данными
     */
    public static void checkData(String[] data) throws DataErrorException {
        try {
            String errorPrefix = "Данные неверны: ";
            StringBuilder errorBuilder = new StringBuilder(errorPrefix);

            try {
                checkId(data[0]);
            } catch (DataErrorException e) {
                addError(errorBuilder, errorPrefix, e.getMessage());
            }

            try {
                checkName(data[1]);
            } catch (DataErrorException e) {
                addError(errorBuilder, errorPrefix, e.getMessage());
            }

            try {
                checkCoordinatesX(data[2]);
            } catch (DataErrorException e) {
                addError(errorBuilder, errorPrefix, e.getMessage());
            }

            try {
                checkCoordinatesY(data[3]);
            } catch (DataErrorException e) {
                addError(errorBuilder, errorPrefix, e.getMessage());
            }

            try {
                checkDate(data[4]);
            } catch (DataErrorException e) {
                addError(errorBuilder, errorPrefix, e.getMessage());
            }

            try {
                checkAnnualTurnover(data[5]);
            } catch (DataErrorException e) {
                addError(errorBuilder, errorPrefix, e.getMessage());
            }

            try {
                checkEmployeesCount(data[6]);
            } catch (DataErrorException e) {
                addError(errorBuilder, errorPrefix, e.getMessage());
            }

            try {
                checkOrganizationType(data[7]);
            } catch (DataErrorException e) {
                addError(errorBuilder, errorPrefix, e.getMessage());
            }

            try {
                checkStreet(data[8]);
            } catch (DataErrorException e) {
                addError(errorBuilder, errorPrefix, e.getMessage());
            }

            try {
                checkZipCode(data[9]);
            } catch (DataErrorException e) {
                addError(errorBuilder, errorPrefix, e.getMessage());
            }

            try {
                checkLocationX(data[10]);
            } catch (DataErrorException e) {
                addError(errorBuilder, errorPrefix, e.getMessage());
            }

            try {
                checkLocationY(data[11]);
            } catch (DataErrorException e) {
                addError(errorBuilder, errorPrefix, e.getMessage());
            }

            try {
                checkLocationZ(data[12]);
            } catch (DataErrorException e) {
                addError(errorBuilder, errorPrefix, e.getMessage());
            }

            if (hasErrors(errorBuilder, errorPrefix)) {
                throw new DataErrorException(errorBuilder.toString());
            }


        } catch (IndexOutOfBoundsException e) {
            throw new DataErrorException("Недостаточно данных");
        }
    }

    /**
     * Вспомогательный метод для метода checkData, добавляет в строку новую ошибку через запятую
     *
     * @param builder builder строки с ошибками ошибок
     * @param prefix  начало строки
     * @param error   название ошибки
     * @see Validator#checkData(String[])
     */
    private static void addError(StringBuilder builder, String prefix, String error) {
        if (builder.length() > prefix.length()) {
            builder.append(", ");
        }
        builder.append(error);
    }

    /**
     * Вспомогательный метод для метода checkData,
     *
     * @param builder builder строки с ошибками ошибок
     * @param prefix  начало строки
     * @return возвращает больше ли длина строки с ошибками, чем минимальная её длина
     * @see Validator#checkData(String[])
     */
    private static boolean hasErrors(StringBuilder builder, String prefix) {
        return builder.length() > prefix.length();
    }

    /**
     * Проверяет наличие аргументов в переданной строке команды, разбитой на токены
     *
     * @param args массив с аргументами
     * @throws DataErrorException пробрасывается при отсутствии аргументов
     */
    public static void checkArgs(String[] args) throws DataErrorException {
        if (args.length < 2 || args[1] == null || args[1].isEmpty()) {
            throw new DataErrorException("Аргументы не указаны или пустые");
        }
    }
}
