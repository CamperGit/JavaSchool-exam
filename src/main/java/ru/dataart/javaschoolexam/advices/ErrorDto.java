package ru.dataart.javaschoolexam.advices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorDto {
    private String message;
}
