package pl.com.bottega.documentmanagement.api.events;

import pl.com.bottega.documentmanagement.domain.Document;
import pl.com.bottega.documentmanagement.domain.events.DocumentListener;

/**
 * Created by Beata Iłowiecka on 27.08.2016.
 */
public class DocumentPublishNotifier implements DocumentListener {

    @Override
    public void published(Document document) {

    }
}
