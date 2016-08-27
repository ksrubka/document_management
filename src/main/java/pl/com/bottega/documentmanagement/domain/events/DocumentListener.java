package pl.com.bottega.documentmanagement.domain.events;

import pl.com.bottega.documentmanagement.domain.Document;

public interface DocumentListener {

    void published(Document document);
}
