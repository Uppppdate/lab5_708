package org.example.data;

import org.example.files.DataErrorException;
import org.example.files.DataParser;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.util.Date;

public class Validator {

    public static boolean isNull(Object obj) throws NullPointerException {
        if (obj == null) {
            throw new NullPointerException();
        }
        return false;
    }

    public static boolean isNull(String input) {
        isNull((Object) input);
        if (input.isEmpty() || input.equals("null")) {
            throw new NullPointerException();
        } else return true;
    }

    public static boolean checkId(String id) throws DataErrorException {
        try {
            Long parsed_id = Long.valueOf(id);
            if (!(parsed_id > IdManager.MIN & parsed_id < IdManager.MAX & !IdManager.checkId(parsed_id))) {
                throw new DataErrorException("Неверное ID");
            }
        } catch (NumberFormatException e) {
            throw new DataErrorException("Неверное ID");
        }
        return true;
    }

    public static boolean checkLong(String longValue) throws DataErrorException {
        try {
            Long.valueOf(longValue);
        } catch (NumberFormatException e) {
            throw new DataErrorException("Данные в неправильном формате");
        }
        return true;
    }

    public static boolean checkName(String name) throws DataErrorException {
        try {
            isNull(name);
        } catch (NullPointerException e) {
            throw new DataErrorException("Wrong name");
        }
        return true;
    }

    public static boolean checkCoordinatesX(String x) throws DataErrorException {
        try {
            isNull(x);
            Long.parseLong(x);
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Wrong x in coordinates");
        }
        return true;
    }

    public static boolean checkCoordinatesY(String y) throws DataErrorException {
        try {
            isNull(y);
            double new_y = Double.parseDouble(y);
            if (new_y > 482) throw new DataErrorException("Wrong y in coordinates");
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Wrong y in coordinates");
        }
        return true;
    }

    public static boolean checkDate(String date) throws DataErrorException {
        try {
            DataParser.formatter.parse(date);
        } catch (ParseException e) {
            throw new DataErrorException("Wrong data");
        }
        return true;
    }

    public static boolean checkAnnualTurnover(String annualTurnover) throws DataErrorException {
        try {
            isNull(annualTurnover);
            float new_annualTurnover = Float.parseFloat(annualTurnover);
            if (new_annualTurnover <= 0) throw new DataErrorException("Wrong annual turnover");
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Wrong annual turnover");
        }
        return true;
    }

    public static boolean checkEmployeesCount(String employeesCount) throws DataErrorException {
        try {
            isNull(employeesCount);
            Integer new_employeesCount = Integer.parseInt(employeesCount);
            if (new_employeesCount <= 0) throw new DataErrorException("Wrong employees count");
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Wrong employees count");
        }
        return true;
    }

    public static boolean checkOrganizationType(String organizationType) throws DataErrorException {
        try {
            isNull(organizationType);
            OrganizationType type = OrganizationType.valueOf(organizationType);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new DataErrorException("Wrong organization type");
        }
        return true;
    }

    public static boolean checkStreet(String street) throws DataErrorException {
        try {
            if (street != null) {
                if (street.length() > 85) throw new DataErrorException("Wrong annual turnover");
            }
        } catch (NullPointerException e) {
            throw new DataErrorException("Wrong annual turnover");
        }
        return true;
    }

    public static boolean checkZipCode(String zipCode) throws DataErrorException {
        try {
            isNull(zipCode);
        } catch (NullPointerException e) {
            throw new DataErrorException("Wrong zip code");
        }
        return true;
    }

    public static boolean checkLocationX(String x) throws DataErrorException {
        try {
            isNull(x);
            double new_x = Double.parseDouble(x);
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Wrong x in coordinates");
        }
        return true;
    }

    public static boolean checkLocationY(String y) throws DataErrorException {
        try {
            isNull(y);
            double new_y = Double.parseDouble(y);
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Wrong y in coordinates");
        }
        return true;
    }

    public static boolean checkLocationZ(String z) throws DataErrorException {
        try {
            isNull(z);
            Long new_z = Long.parseLong(z);
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Wrong z in coordinates");
        }
        return true;
    }


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
            throw new DataErrorException("Data is not correct:\n\t" + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new DataErrorException("Insufficient data");
        }
    }

    public static boolean checkArgs(String[] args) throws DataErrorException {
        if (args.length < 2 || args[1] == null || args[1].isEmpty()) {
            throw new DataErrorException("Аргументы не указаны или пустые");
        }
        return true;
    }
}
