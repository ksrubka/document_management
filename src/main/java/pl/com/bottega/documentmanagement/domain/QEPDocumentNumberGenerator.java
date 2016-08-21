package pl.com.bottega.documentmanagement.domain;

import org.springframework.stereotype.Service;

public class QEPDocumentNumberGenerator implements DocumentNumberGenerator {
    @Override
    public DocumentNumber generate() {
        return new DocumentNumber("QEP/" + (int)(Math.random() * 100) + "/" + (int)(Math.random() * 100));
    }
}
