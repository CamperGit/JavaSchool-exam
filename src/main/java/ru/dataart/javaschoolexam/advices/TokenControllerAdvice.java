package ru.dataart.javaschoolexam.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.dataart.javaschoolexam.exceptions.NotZipFileException;
import ru.dataart.javaschoolexam.exceptions.WrongArticleFormatException;

import java.util.Date;
import java.util.zip.ZipException;

@RestControllerAdvice
public class TokenControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDto handleRuntimeException(RuntimeException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler(value = WrongArticleFormatException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ErrorDto handleWrongArticleFormatException(WrongArticleFormatException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler(value = NotZipFileException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ErrorDto handleNotZipFileException() {
        return new ErrorDto("Архив должен быть в .zip формате");
    }
}
