package pl.com.bottega.documentmanagement.domain;

import org.springframework.stereotype.Service;

import java.util.UUID;

public class ISODocumentNumberGenerator implements DocumentNumberGenerator {

    @Override
    public DocumentNumber generate() {
        return new DocumentNumber("ISO-" + UUID.randomUUID().toString());
    }

}
