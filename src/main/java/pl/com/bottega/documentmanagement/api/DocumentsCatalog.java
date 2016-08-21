package pl.com.bottega.documentmanagement.api;

import pl.com.bottega.documentmanagement.domain.DocumentNumber;

public interface DocumentsCatalog {
    DocumentDto get(DocumentNumber documentNumber);

    DocumentSearchResults find(DocumentCriteria documentCriteria);
}
