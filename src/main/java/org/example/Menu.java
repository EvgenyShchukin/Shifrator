package org.example;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        while (true) {
            System.out.println("""
                    Выберите действие введя его номер:
                    1 - Зашифровать текст в файле
                    2 - Расшифровать текст в файле
                    3 - Подобрать ключ с помощью брутофорса
                    4 - Синтаксический анализ текста с стат. анализа
                    5 - Выйти из программы"""
            );

            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();

            switch (answer) {
                case "1" -> new Encrypted().encrypted();
                case "2" -> new Decrypted().decrypted();
                case "3" -> System.out.println("action3");
                case "4" -> System.out.println("action4");
                case "5" -> {
                    return;
                }
            }
        }
    }
}