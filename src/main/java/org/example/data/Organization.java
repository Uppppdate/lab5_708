package org.example.data;

import org.example.files.DataParser;

import java.util.Date;

/**
 * Класс Organization описывает объект, хранящийся в коллекции
 */
public class Organization implements Comparable<Organization> {
    private Long id;
    private final String name;
    private final Coordinates coordinates;
    private final java.util.Date creationDate;
    private final float annualTurnover;
    private final Integer employeesCount;
    private final OrganizationType type;
    private final Address officialAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getEmployeesCount() {
        return employeesCount;
    }

    public Organization(Long id, String name, Coordinates coordinates, Date creationDate, float annualTurnover, Integer employeesCount, OrganizationType type, Address officialAddress) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
        this.type = type;
        this.officialAddress = officialAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Организация: " + name +
                ", ID: " + id +
                ", Координаты: " + coordinates +
                ", Дата: " + DataParser.formatter.format(creationDate) +
                ", Годовой оборот: " + annualTurnover +
                ", Количество работников: " + employeesCount +
                ", Тип: " + type +
                ", Адрес: ");
        if (officialAddress == null) {
            sb.append("Неизвестен");
        } else sb.append(officialAddress);
        return sb.toString();
    }


    /**
     * @return строковое представление для сохранения в файл csv
     */
    public String toCsv() {
        char del = ',';
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(del).append(name).append(del).append(coordinates.toCsv()).append(del)
                .append(DataParser.formatter.format(creationDate)).append(del)
                .append(annualTurnover).append(del).append(employeesCount).append(del)
                .append(type.name()).append(del);
        if (officialAddress == null) {
            sb.append(Address.toCsvDefault());
        } else {
            sb.append(officialAddress.toCsv());
        }
        sb.append('\n');
        return sb.toString();
    }

    /**
     * Сравнивает два объекта с определённой логикой,
     * позволяет сортировать в прямом и обратном порядке организации
     *
     * @param o объект для сравнения
     * @return результат сравнения
     */
    @Override
    public int compareTo(Organization o) {
        // 1. Сравниваем по масштабу (оборот × сотрудники)
        double thisScale = this.annualTurnover * this.employeesCount;
        double otherScale = o.annualTurnover * o.employeesCount;
        int scaleComparison = Double.compare(thisScale, otherScale);
        if (scaleComparison != 0) return scaleComparison;

        // 2. При равном масштабе сравниваем по эффективности (оборот / сотрудник)
        double thisEfficiency = this.annualTurnover / this.employeesCount;
        double otherEfficiency = o.annualTurnover / o.employeesCount;
        int efficiencyComparison = Double.compare(thisEfficiency, otherEfficiency);
        if (efficiencyComparison != 0) return efficiencyComparison;

        // 3. Если всё равно одинаково — сравниваем по ID (уникальный параметр)
        return Long.compare(this.id, o.id);
    }
}
