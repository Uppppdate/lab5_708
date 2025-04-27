package org.example.commands;

import org.example.managers.CollectionManager;

public class AverageOfEmployeesCountCommand extends BaseCommand{

    public AverageOfEmployeesCountCommand() {
        super("average_of_employees_count", "вывести среднее значение поля employeesCount для всех элементов коллекции", new String[]{});
    }
    private static Integer amount = 0;
    @Override
    public String execute(String[] args) {
        CollectionManager.getOrgSet().forEach(org -> amount += org.getEmployeesCount());
        float avg = (float) amount /CollectionManager.getOrgSet().size();
        System.out.println("Среднее количество работников: " + avg);
        amount = 0;
        return null;
    }
}
