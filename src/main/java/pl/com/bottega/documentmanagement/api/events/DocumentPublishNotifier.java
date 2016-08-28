package pl.com.bottega.documentmanagement.api.events;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;
import pl.com.bottega.documentmanagement.api.EmployeeDetails;
import pl.com.bottega.documentmanagement.api.facades.HRSystemFacade;
import pl.com.bottega.documentmanagement.api.facades.MailingFacade;
import pl.com.bottega.documentmanagement.domain.Document;
import pl.com.bottega.documentmanagement.domain.EmployeeId;
import pl.com.bottega.documentmanagement.domain.Reader;
import pl.com.bottega.documentmanagement.domain.events.DocumentListener;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DocumentPublishNotifier implements DocumentListener {

    private MailingFacade mailingFacade;
    private HRSystemFacade hrSystemFacade;

    public DocumentPublishNotifier(MailingFacade mailingFacade, HRSystemFacade hrSystemFacade) {
        this.mailingFacade = mailingFacade;
        this.hrSystemFacade = hrSystemFacade;
    }

    @Override
    public void published(Document document) {
        Set<Reader> readers = document.readers();
        Set<EmployeeId> ids = readers.stream().map(Reader::employeeId).collect(Collectors.toSet());
        Set<EmployeeDetails> employeeDetailsSet = hrSystemFacade.getEmployeeDetails(Sets.newHashSet(ids));
        sendEmailAboutPublishedDocument(document, employeeDetailsSet);
    }

    private void sendEmailAboutPublishedDocument(Document document, Set<EmployeeDetails> employeeDetails) {
        Set<EmployeeDetails> employeesHavingEmail = employeeDetails.stream()
                .filter(EmployeeDetails::hasEmail)
                .collect(Collectors.toSet());
        mailingFacade.sendDocumentPublishedEmails(document, employeesHavingEmail);
    }
}
