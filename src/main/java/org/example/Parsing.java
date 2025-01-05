package org.example;

import lombok.SneakyThrows;
import lombok.Value;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Parsing {
    @SneakyThrows
    public void parsing() {
        ConsoleHelper.writeMessage("Введите путь к файлу для его расшифровки");
        String src = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите путь к файлу для набора статистики");
        String srcStatic = ConsoleHelper.readString();
        Path dst = ConsoleHelper.buildFileName(src, "_parsing");

        Map<Character, Integer> mapEncrypted = getMap(src);
        Map<Character, Integer> mapStatistic = getMap(srcStatic);

        List<Map.Entry<Character, Integer>> listEncrypted = mapToInt(mapEncrypted);
        List<Map.Entry<Character, Integer>> listStatistic = mapToInt(mapStatistic);

        Map<Character, Character> mapDecrypted = new HashMap<>();
        if (listEncrypted.size() <= listStatistic.size()) {
            for (int i = 0; i < listEncrypted.size(); i++) {
                mapDecrypted.put(listEncrypted.get(i).getKey(), listStatistic.get(i).getKey());
            }
            String textEncrypt = Files.readString(Path.of(src));
            char[] array = textEncrypt.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (char aChaar : array) {
                char simvol = mapDecrypted.get(aChaar);
                builder.append(simvol);
            }
            Files.writeString(dst, builder);
        } else {
            ConsoleHelper.writeMessage("Размер файла статистики не соответствует по критериям расщифровки");
        }
        ConsoleHelper.writeMessage("Содержимое было записано!");
    }

    public List<Map.Entry<Character, Integer>> mapToInt (Map<Character, Integer> map) {
        Set<Map.Entry<Character, Integer>> entries = map.entrySet();
        ArrayList<Map.Entry<Character, Integer>> list = new ArrayList<>(entries);
        list.sort((a, b) -> b.getValue() - a.getValue());
        return list;
    }

    @SneakyThrows
    private Map<Character, Integer> getMap(String path) {
        String content = Files.readString(Path.of(path));
        Map<Character, Integer> map = new HashMap<>();
        char[] charArray = content.toCharArray();
        for (char aChar : charArray) {
            if (!map.containsKey(aChar)) {
                map.put(aChar, 1);
            } else {
                int value = map.get(aChar);
                map.put(aChar, value + 1);
            }
        }
        return map;
    }
}