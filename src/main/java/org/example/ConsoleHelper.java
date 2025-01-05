package org.example;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class ConsoleHelper {
    private static final BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    private ConsoleHelper() {
    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    @SneakyThrows
    public static String readString() {
        return console.readLine();
    }

    public static int readInt() {
        return Integer.parseInt(readString());
    }

    public static Path buildFileName(String path, String suffix) {
        Path fullPath = Path.of(path);
        String fileName = fullPath.getFileName().toString();
        String newFileName = "";
        if (fileName.contains(".")) {
            int index = fileName.indexOf(".");
            newFileName = fileName.substring(0, index) + suffix + fileName.substring(index);
        } else {
            newFileName = fileName + suffix;
        }

        return fullPath.getParent().resolve(newFileName);
    }
}
