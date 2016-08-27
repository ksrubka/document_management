package pl.com.bottega.wzorce.decorator.cesar_cipher;

import java.io.IOException;
import java.io.InputStream;

public class CesarInputStream extends InputStream {

    private int key;
    private InputStream decorated;

    public CesarInputStream(InputStream inputStream, int key) {
        this.key = key;
        this.decorated = inputStream;
    }

    @Override
    public int read() throws IOException {
        int read = decorated.read();
        if (read != -1) {
            return read - key;
        }
        return read;
    }
}
