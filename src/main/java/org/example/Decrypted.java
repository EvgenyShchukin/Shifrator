package org.example;

import java.io.*;
import java.util.Scanner;

public class Decrypted {
    public void decrypted () {
        System.out.println("Введите адрес для расшифровки файла");
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
                String resultDecrypt = caesarCipher.decrypt(line, key);
                writer.write(resultDecrypt);
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Файл успешно расшифрован");
    }
}
