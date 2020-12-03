package ru.lachesis.lesson3;

import java.util.HashMap;
import java.util.Map;

public class MainClass {
    public static void main(String[] args) {
        String[] words = new String[]{
                "яблоко", "тыква", "огурец", "молоко", "хлеб",
                "ягода", "масло", "сыр", "молоко", "хлеб",
                "тунец", "вишня", "дерево", "молоко", "хлеб",
                "яблоко", "тыква", "огурец", "клубника", "ромашка"

        };

        Map<String, Integer> wordcount = new HashMap<>();
        for (String word : words) {
            if (!wordcount.containsKey(word)) wordcount.put(word, 1);
            else wordcount.put(word, wordcount.get(word) + 1);
        }
        System.out.println(wordcount);
        System.out.println();

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "223-44-66");
        phoneBook.add("Cat", "111");
        phoneBook.add("йцук", "180-00-81");
        phoneBook.add("йцук", "180-00-81");
        phoneBook.add("йцук", "180-00-82");
        phoneBook.add("Иванов", "222-22-22");
        phoneBook.add("Cat", "112-33-44");
        phoneBook.add("Res", "111-21-11");
        phoneBook.add("Иванова", "222-22-33");
        String name ="Иванов";
        System.out.println(name +" : "+phoneBook.get(name));
    }
}
