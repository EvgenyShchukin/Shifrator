package org.example;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.Scanner;

public class EncryptedDecrypted {
    @SneakyThrows
    public void encryptedDecrypted(boolean flag) {

        System.out.println("Введите адрес для " + (flag ? "зашифровки" : "расифровки") + " файла");
        Scanner input = new Scanner(System.in);
        String src = input.nextLine();
        System.out.println("Введите ключ");
        int key = Integer.parseInt(input.nextLine());
        Path dst = ConsoleHelper.buildFileName(src, flag ? "_encrypt" : "_decrypt");

        CaesarCipher caesarCipher = new CaesarCipher();
        try (BufferedReader reader = new BufferedReader(new FileReader(src));
             BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(dst)));) {
            while (reader.ready()) {
                String line = reader.readLine() + System.lineSeparator();
                String result = flag ? caesarCipher.encrypt(line, key) : caesarCipher.decrypt(line, key);
                writer.write(result);
         //       writer.newLine();
            }
        }
        System.out.println("Файл успешно " + (flag ? "зашифрован" : "расшифрован"));
    }
}
