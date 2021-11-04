package ru.dataart.javaschoolexam.exceptions;

public class WrongArticleFormatException extends RuntimeException{
    public WrongArticleFormatException(String message) {
        super("Ошибка формата архива: " + message);
    }
}
