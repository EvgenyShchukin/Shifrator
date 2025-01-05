package org.example;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readString;

public class Bruteforce {
    @SneakyThrows
    public void bruteforce() {
        ConsoleHelper.writeMessage("Введите адрес файла для его расшифровки");
        String src = ConsoleHelper.readString();
        Path dst = ConsoleHelper.buildFileName(src, "_bruteforce");
        String content = Files.readString(Path.of(src));
        CaesarCipher caesarCipher = new CaesarCipher();
        for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
            String result = caesarCipher.decrypt(content, i);
            if (isValidate(result)) {
                Files.writeString(dst, result);
                ConsoleHelper.writeMessage("Содержимое расшифровано, ключ расшифровки " + i);
                break;
            }
        }

    }
    @SneakyThrows
    public void bruteforce1() {
        ConsoleHelper.writeMessage("Введите адрес файла для его расшифровки");
        String src = ConsoleHelper.readString();
        Path dst = ConsoleHelper.buildFileName(src, "_bruteforce");
        List<String> list = Files.readAllLines(Path.of(src));
        String join = String.join("", list);
        try (BufferedReader reader = new BufferedReader(new FileReader(src));
             BufferedWriter writer = Files.newBufferedWriter(dst);) {
            CaesarCipher caesarCipher = new CaesarCipher();
            StringBuilder builder = new StringBuilder();
            List<String> strings = new ArrayList<>();
            while (reader.ready()) {
                String line = reader.readLine();
                builder.append(line);
                strings.add(line);
            }
            for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
                String result = caesarCipher.decrypt(builder.toString(), i);
                if (isValidate(result)) {
                    for (String string : strings) {
                        String newResult = caesarCipher.decrypt(string, i);
                        writer.write(newResult);
                        writer.newLine();
                    }
                    ConsoleHelper.writeMessage("Содержимое расшифровано, ключ расшифровки " + i);
                    break;
                }
            }
        }
    }

    private boolean isValidate(String text) {
        boolean isCheck = false;
        String[] strings = text.split(" ");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].length() > 28) {
                return false;
            }
        }
        if (text.contains(", ")) {
            isCheck = true;
        }
        ConsoleHelper.writeMessage((text.length() > 200) ? text.substring(0, 200) : text);
        while (isCheck) {
            ConsoleHelper.writeMessage("Понятен ли вам текст?");
            ConsoleHelper.writeMessage("Введите ДА или НЕТ");
            String resultOutput = ConsoleHelper.readString();
            if (resultOutput.equalsIgnoreCase("ДА")) {
                return true;
            } else if (resultOutput.equalsIgnoreCase("НЕТ")) {
                isCheck = false;
            } else {
                ConsoleHelper.writeMessage("Введите либо ДА либо НЕТ");
            }
        }
        return false;
    }
}
