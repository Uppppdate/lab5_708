package org.example.commands;

/**
 * Абстрактный класс для описания общего устройства всех команд
 */
public abstract class BaseCommand implements Command{
    /**
     * Имя команды
     */
    private final String name;
    /**
     * Описание команды
     */
    private final String description;

    /**
     * Аргументы команды
     */
    private final String[] arguments;

    /**
     * Базовый конструктор
     * @param name имя
     * @param description описание
     * @param arguments аргументы
     */
    public BaseCommand(String name, String description, String[] arguments) {
        this.name = name;
        this.description = description;
        this.arguments = arguments;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getArguments() {
        return arguments;
    }

    public int getArgAmount(){
        return arguments.length;
    }

    /**
     * @return возвращает строку, содержащую в нужном виде информацию о команде
     */
    public String getHelp() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(name);
        for(String argument : arguments) {
            sb.append(' ').append(argument);
        }
        sb
                .append(" - ")
                .append(description);
        return sb.toString();
    }
}
