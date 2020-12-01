package ru.lachesis.lesson2;

public class MainClass {
    final static int SIZE = 4;
    static public int row = 0;
    static public int col = 0;
    static public int sum = 0;
    static int level = 0;
    static String[][] array = new String[][]{
//            {"qq", "qs", "qq", "qq"},
//            {"qq", "qs", "qq", "qq"},
//            {"qq", "qs", "qq", "qq"},
//            {"qq", "qs", "qq"}

//            {"11", "11", "11", "11"},
//            {"11", "11", "11", "11"},
//            {"11", "11", "11", "11"},
//            {"11", "11", "11", "11"}

            {"12", "3", "щ", "5"},
            {"25", "44", "3d", "7"},
            {"112", "23", "31", "1"},
            {"788", "struct", "dd", "3333"}
    };

    public static void main(String[] args) {
        try {
            sum = testExceptions(array);
        } catch (MyArraySizeException e) {

            System.out.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
            sum = e.getSum();
            System.out.println("Промежуточная сумма = " + sum + ", поехали дальше!");
            row = e.getRow();
            col = e.getCol();
            if (col == SIZE - 1 && row < SIZE - 1) {
                col = 0;
                row = row + 1;
            } else {
                col++;
            }
            main(args);
            level++;
        }
        if (level == 0)
            System.out.println("\nСумма числовых элементов массива = " + sum);
    }

    public static int testExceptions(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        boolean isTrueSize;
        try {
            isTrueSize = arr.length == SIZE;
            int i = 0;
            while (isTrueSize && i < arr.length) {
                isTrueSize = arr[i].length == SIZE;
                i++;
            }
        } catch (NullPointerException e) {
            System.out.println("Ошибка! Массив неопределен");
            isTrueSize = false;
        }
        if (!isTrueSize) throw new MyArraySizeException();

        //       int sum=0;
        for (int i = row; i < arr.length; i++) {
            for (int j = col; j < arr[i].length; j++) {
                if (col == SIZE - 1) col = 0;
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (Exception e) {
                    throw new MyArrayDataException(i, j, sum);

                }

            }
        }

        return sum;
    }

}



