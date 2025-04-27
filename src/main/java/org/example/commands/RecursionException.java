package org.example.commands;

/**
 * Ошибка, пробрасываемая при обнаружении рекурсии в файле
 */
public class RecursionException extends Exception{
    public RecursionException(String message) {
        super("Рекурсия в файле: " + message);
    }
}
