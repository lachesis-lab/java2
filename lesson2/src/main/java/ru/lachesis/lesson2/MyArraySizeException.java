package ru.lachesis.lesson2;

public class MyArraySizeException extends Exception {

    @Override
    public String getMessage() {
        return "Ошибка размерности массива!";
    }
}
