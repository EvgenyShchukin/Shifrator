package org.example;

public class CaesarCipher {
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            ".,\":!? +-*/\\@#$%^&(){}[];'|`~=_©«»—" + "0123456789" + (char)10 + (char)13;

    public String encrypt(String message, int key) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char charAt = message.charAt(i);
            int index = alphabet.indexOf(charAt);
            if (index >= 0) {
                int newIndex = (index + key) % alphabet.length();
                char newChar;
                if (newIndex < 0) {
                    newChar = alphabet.charAt(newIndex + alphabet.length());
                } else {
                    newChar = alphabet.charAt(newIndex);
                }
                stringBuilder.append(newChar);
            }
        }

        return stringBuilder.toString();
    }

    public String decrypt(String message, int key) {
        return encrypt(message, key * -1);
    }

    public int alphabetLength () {
        return  alphabet.length();
    }
}
