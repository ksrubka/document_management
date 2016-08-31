package pl.com.bottega.wzorce.decorator.cesar_cipher;

import java.io.*;

/**
 * Created by Beata Iłowiecka on 27.08.2016.
 */
public class CaesarCipherApplication {

    public static void main(String[] args) throws IOException {

        OutputStream outputStream = new FileOutputStream("C:/projects/dokumenty/caesarCipher.txt");
        int key = 133;
        outputStream = new CesarOutputStream(outputStream, key);
        outputStream.write("Ala ma kota i aligatoraz".getBytes());
        outputStream.close();

        InputStream inputStream = new FileInputStream("C:/projects/dokumenty/caesarCipher.txt");
        inputStream = new CesarInputStream(inputStream, key);

        int intCharacter = inputStream.read();
        StringBuilder sb = new StringBuilder();
        char character;
        while (intCharacter != -1) {
            character = (char) intCharacter;
            sb.append(character);
            intCharacter = inputStream.read();
        }
        System.out.println(sb);
        inputStream.close();
    }
}
