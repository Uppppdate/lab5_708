package org.example.commands;

import org.example.managers.CollectionManager;
import static org.example.Main.clm;

/**
 * Команда AverageOfEmployeesCountCommand
 */
public class AverageOfEmployeesCountCommand extends BaseCommand {

    /**
     * Конструктор
     */
    public AverageOfEmployeesCountCommand() {
        super("average_of_employees_count", "вывести среднее значение поля employeesCount для всех элементов коллекции", new String[]{});
    }

    /**
     * Переменная для работы лямбда выражения в методе execute
     */
    private static Integer amount = 0;

    /**
     * Метод, выполняющий команду average_of_employees_count
     * @param args не используется здесь
     */
    @Override
    public void execute(String[] args) {
        clm.getOrgSet().forEach(org -> amount += org.getEmployeesCount());
        float avg = (float) amount / clm.getOrgSet().size();
        System.out.println("Среднее количество работников: " + avg);
        amount = 0;
    }
}
