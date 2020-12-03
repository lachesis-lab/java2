package ru.lachesis.lesson3;

import java.util.ArrayList;
import java.util.Collections;

public class PhoneBook {
    private final ArrayList<Abonent> phoneBook = new ArrayList<>();

    public void add(String name, String phone) {
        Abonent abonent = new Abonent(name, phone);
        phoneBook.add(abonent);
    }

    public ArrayList<String> get(String name) {
        ArrayList<String> result = new ArrayList<>();
        Collections.sort(phoneBook);
//        System.out.println(phoneBook);
        int lastEntry = phoneBook.size();
        for (int i = 0; i < phoneBook.size(); i++) {
            if (name.equals(phoneBook.get(i).name)) {
                lastEntry = i;
                result.add(phoneBook.get(i).phone);
            } else if (i > lastEntry) break;
        }
        return result;
    }

    private class Abonent implements Comparable<Object> {
        private String name;
        private String phone;

        private Abonent(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }

        @Override
        public int compareTo(Object o) {
            Abonent abonent = (Abonent) o;
            return this.name.compareTo(abonent.name);
        }

        @Override
        public String toString() {
            return name + " : " + phone;
        }
    }

}
