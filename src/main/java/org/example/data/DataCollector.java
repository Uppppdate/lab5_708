package org.example.data;

import org.example.files.DataErrorException;
import org.example.files.DataParser;

import java.util.Date;
import java.util.Scanner;

/**
 * Класс, сборщик данных для команд с вводом по несколько строк.
 */
public class DataCollector {
    /**
     * Метод для сборки данных из скрипта
     *
     * @param sc Сканер для чтения файла с того, места, где была вызвана команда.
     *           При отработке метода сканер продолжает чтение файла с места, где закончилась
     *           составная команда.
     * @return массив с собранными данными
     */
    public static String[] collectDataFromScript(Scanner sc) {
        String[] data = new String[12];
        for (int i = 0; i < data.length; i++) {
            if (sc.hasNext()) {
                data[i] = sc.nextLine();
            }
        }
        return data;
    }

    /**
     * Метод для сборки данных из консоли
     *
     * @param sc Сканер для чтения данных из консоли
     * @return массив с собранными данными
     */
    public static String[] collectDataFromConsole(Scanner sc) {
        //массив со всеми данными
        String[] data = new String[12];

        //ввод имени
        System.out.println("Введите имя: ");
        String name = "";
        while (true) {
            try {
                name = sc.nextLine();
                Validator.checkName(name);
                break;
            } catch (DataErrorException e) {
                System.out.println(e.getMessage());
            }
        }

        //ввод X
        System.out.println("Введите координату X: ");
        String x = "";
        while (true) {
            try {
                x = sc.nextLine();
                Validator.checkCoordinatesX(x);
                break;
            } catch (DataErrorException e) {
                System.out.println(e.getMessage());
                System.out.println("X - целое число");
            }
        }

        //ввод Y
        System.out.println("Введите координату Y: ");
        String y = "";
        while (true) {
            try {
                y = sc.nextLine();
                Validator.checkCoordinatesY(y);
                break;
            } catch (DataErrorException e) {
                System.out.println(e.getMessage());
                System.out.println("Y - дробное число, меньше 482");
            }
        }

        //ввод даты
        System.out.println("Введите координату дату в формате: yyyy-mm-dd HH:MM:SS");
        System.out.println("Или введите 'current', чтобы указать текущую дату");
        String date = "";
        while (true) {
            try {
                date = sc.nextLine();
                Validator.checkDate(date);
                if(date.equals("current")){
                    date = DataParser.formatter.format(new Date().getTime());
                }
                break;
            } catch (DataErrorException e) {
                System.out.println(e.getMessage());
                System.out.println("Формат даты: yyyy-mm-dd HH:MM:SS");
                System.out.println("'current' - текущая дата");
            }
        }

        //ввод годового оборота
        System.out.println("Введите годовой оборот");
        String annualTurnover = "";
        while (true) {
            try {
                annualTurnover = sc.nextLine();
                Validator.checkAnnualTurnover(annualTurnover);
                break;
            } catch (DataErrorException e) {
                System.out.println(e.getMessage());
                System.out.println("Годовой оборот - нецелое число, больше 0");
            }
        }

        //ввод количества работников
        System.out.println("Введите количество работников");
        String employeesCount = "";
        while (true) {
            try {
                employeesCount = sc.nextLine();
                Validator.checkEmployeesCount(employeesCount);
                break;
            } catch (DataErrorException e) {
                System.out.println(e.getMessage());
                System.out.println("Количество работников - целое число, больше 0");
            }
        }

        //ввод типа
        System.out.println("Введите тип организации:");
        System.out.println(OrganizationType.PUBLIC.name());
        System.out.println(OrganizationType.GOVERNMENT.name());
        System.out.println(OrganizationType.COMMERCIAL.name());
        System.out.println(OrganizationType.OPEN_JOINT_STOCK_COMPANY.name());
        System.out.println(OrganizationType.TRUST.name());
        String type = "";
        while (true) {
            try {
                type = sc.nextLine();
                Validator.checkOrganizationType(type);
                break;
            } catch (DataErrorException e) {
                System.out.println(e.getMessage());
            }
        }

        //ввод улицы
        System.out.println("Введите улицу");
        System.out.println("Если улица неизвестна, укажите '0'");
        String street = "";
        while (true) {
            try {
                street = sc.nextLine();
                Validator.checkStreet(street);
                break;
            } catch (DataErrorException e) {
                System.out.println(e.getMessage());
                System.out.println("Длина должна быть меньше 85");
            }
        }

        //ввод индекса
        System.out.println("Введите индекс");
        System.out.println("Если индекс неизвестен, укажите '0'");
        String zipCode = "";
        while (true) {
            try {
                zipCode = sc.nextLine();
                Validator.checkZipCode(zipCode);
                break;
            } catch (DataErrorException e) {
                System.out.println(e.getMessage());
            }
        }

        //ввод координаты x локации
        System.out.println("Введите координату X для локации");
        System.out.println("Если неизвестна, укажите '0'");
        String X = "";
        while (true) {
            try {
                X = sc.nextLine();
                Validator.checkLocationX(X);
                break;
            } catch (DataErrorException e) {
                System.out.println(e.getMessage());
            }
        }

        //ввод координаты y локации
        System.out.println("Введите координату Y для локации");
        System.out.println("Если неизвестна, укажите '0'");
        String Y = "";
        while (true) {
            try {
                Y = sc.nextLine();
                Validator.checkLocationY(Y);
                break;
            } catch (DataErrorException e) {
                System.out.println(e.getMessage());
            }
        }

        //ввод координаты z локации
        System.out.println("Введите координату Z для локации");
        System.out.println("Если неизвестна, укажите '0'");
        String Z = "";
        while (true) {
            try {
                Z = sc.nextLine();
                Validator.checkLocationZ(Z);
                break;
            } catch (DataErrorException e) {
                System.out.println(e.getMessage());
                System.out.println("Координата Z для локации - целое число");
            }
        }

        //записываю всё полученное в массив
        data[0] = name;
        data[1] = x;
        data[2] = y;
        data[3] = date;
        data[4] = annualTurnover;
        data[5] = employeesCount;
        data[6] = type;
        data[7] = street;
        data[8] = zipCode;
        data[9] = X;
        data[10] = Y;
        data[11] = Z;

        return data;
    }
}
