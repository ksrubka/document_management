package pl.com.bottega.wzorce.decorator.cesar_cipher;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Beata Iłowiecka on 27.08.2016.
 */
public class CesarOutputStream extends OutputStream {

    private int key;
    private OutputStream decorated;

    public CesarOutputStream(OutputStream outputStream, int key) {
        this.key = key;
        this.decorated = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        int bCiphered = b + key;
        decorated.write(bCiphered);
    }
}
