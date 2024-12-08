package org.example;

import lombok.SneakyThrows;

import java.io.*;
import java.util.Scanner;

public class Encrypted {
    @SneakyThrows
    public void encrypted() {
        System.out.println("Введите адрес для зашифровки файла");
        Scanner input = new Scanner(System.in);
        String src = input.nextLine();
        System.out.println("Введите ключ");
        int key = Integer.parseInt(input.nextLine());
        System.out.println("Введите адрес куда записать результат");
        String dst = input.nextLine();

        CaesarCipher caesarCipher = new CaesarCipher();
        try (BufferedReader reader = new BufferedReader(new FileReader(src));
             BufferedWriter writer = new BufferedWriter(new FileWriter(dst));) {
            while (reader.ready()) {
                String line = reader.readLine();
                String resultEncrypt = caesarCipher.encrypt(line, key);
                writer.write(resultEncrypt);
                writer.newLine();
            }
        }
        System.out.println("Файл успешно зашифрован");
    }
}
