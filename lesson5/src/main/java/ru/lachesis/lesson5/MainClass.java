package ru.lachesis.lesson5;

import java.util.Arrays;

public class MainClass {

    public static void main(String[] args) {
        simpleMethod();
        try {
            threadMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void simpleMethod() {
        final int SIZE = 10_000_000;
        final float[] array = new float[SIZE];
        Arrays.fill(array, 1);
        long a = System.currentTimeMillis();
        countFormula(array);
        System.out.println("1 поток: "+(System.currentTimeMillis() - a) + " ms");
    }

    private static void threadMethod() throws InterruptedException {
        final int SIZE = 10_000_001;
        final float[] array = new float[SIZE];
        Arrays.fill(array, 1);
        final int h = SIZE / 2;
        float[] arr1 = new float[h];
        float[] arr2 = new float[SIZE - h];
        long a = System.currentTimeMillis();
        System.arraycopy(array, 0, arr1, 0, h);
        System.arraycopy(array, h, arr2, 0, SIZE - h);
        Thread t1 = new Thread(() -> countFormula(arr1));
        Thread t2 = new Thread(() -> countFormula(arr2));

        t1.start();
        t2.start();
        //ждем окончания рассчета
        t1.join();
        t2.join();

        System.arraycopy(arr1, 0, array, 0, h);
        System.arraycopy(arr2, 0, array, h, SIZE - h);

        System.out.println("2 потока: "+ (System.currentTimeMillis() - a) + " ms");
//        System.out.println(Arrays.toString(array));

    }

    private static void countFormula(float[] array) {
        for (int i = 1; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

    }

}

