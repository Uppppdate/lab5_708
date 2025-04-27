package org.example.data;

import org.example.files.DataErrorException;

import java.io.InputStream;
import java.util.Scanner;

public class DataCollector {
    public static String[] collectDataFromScript(Scanner sc) {
        String[] data = new String[12];
        for (int i = 0; i < data.length; i++) {
            if (sc.hasNext()) {
                data[i] = sc.nextLine();
            }
        }
        return data;
    }

    public static String[] collectDataFromConsole(Scanner sc) {
        //массив со всеми данными
        String[] data = new String[12];

        //ввод имени
        System.out.println("Введите имя: ");
        String name = "";
        while (true) {
            try {
                if (sc.hasNext()) {
                    name = sc.nextLine();
                    Validator.checkName(name);
                    break;
                } else {
                    System.out.println("Неверный ввод");
                }
            } catch (DataErrorException e) {
                System.out.println(e.getMessage());
            }
        }

        //ввод X
        System.out.println("Введите координату X: ");
        String x = "";
        while (true) {
            try {
                if (sc.hasNext()) {
                    x = sc.nextLine();
                    Validator.checkCoordinatesX(x);
                    break;
                } else {
                    System.out.println("Неверный ввод");
                }
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
                if (sc.hasNext()) {
                    y = sc.nextLine();
                    Validator.checkCoordinatesY(y);
                    break;
                } else {
                    System.out.println("Неверный ввод");
                }
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
                if (sc.hasNext()) {
                    date = sc.nextLine();
                    Validator.checkDate(date);
                    break;
                } else {
                    System.out.println("Неверный ввод");
                }
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
                if (sc.hasNext()) {
                    annualTurnover = sc.nextLine();
                    Validator.checkAnnualTurnover(annualTurnover);
                    break;
                } else {
                    System.out.println("Неверный ввод");
                }
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
                if (sc.hasNext()) {
                    employeesCount = sc.nextLine();
                    Validator.checkEmployeesCount(employeesCount);
                    break;
                } else {
                    System.out.println("Неверный ввод");
                }
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
                if (sc.hasNext()) {
                    type = sc.nextLine();
                    Validator.checkOrganizationType(type);
                    break;
                } else {
                    System.out.println("Неверный ввод");
                }
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
                if (sc.hasNext()) {
                    street = sc.nextLine();
                    Validator.checkStreet(street);
                    break;
                } else {
                    System.out.println("Неверный ввод");
                }
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
                if (sc.hasNext()) {
                    zipCode = sc.nextLine();
                    Validator.checkZipCode(zipCode);
                    break;
                } else {
                    System.out.println("Неверный ввод");
                }
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
                if (sc.hasNext()) {
                    X = sc.nextLine();
                    Validator.checkLocationX(X);
                    break;
                } else {
                    System.out.println("Неверный ввод");
                }
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
                if (sc.hasNext()) {
                    Y = sc.nextLine();
                    Validator.checkLocationY(Y);
                    break;
                } else {
                    System.out.println("Неверный ввод");
                }
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
                if (sc.hasNext()) {
                    Z = sc.nextLine();
                    Validator.checkLocationZ(Z);
                    break;
                } else {
                    System.out.println("Неверный ввод");
                }
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
