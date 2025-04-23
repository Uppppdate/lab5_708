package org.example.commands;

public abstract class BaseCommand implements Command{
    private final String name;
    private final String description;

    private final String[] arguments;

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
