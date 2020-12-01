package ru.lachesis.lesson2;

public class MyArrayDataException extends Exception {
    private int row, col, sum;


    public MyArrayDataException(int row, int col, int sum) {
        super();
        this.row = row;
        this.col = col;
        this.sum = sum;
    }

    @Override
    public String getMessage() {
//        return super.getMessage();
        return "Ошибка! Не числовое значение в позиции [" + row + "]" + "[" + col + "]";
    }

    public int getSum() {
        return sum;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
