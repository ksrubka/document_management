package pl.com.bottega.documentmanagement.api.facades.implementations;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import pl.com.bottega.documentmanagement.api.EmployeeDetails;
import pl.com.bottega.documentmanagement.api.facades.MailingFacade;
import pl.com.bottega.documentmanagement.api.facades.PrintSystemFacade;
import pl.com.bottega.documentmanagement.domain.Document;

import java.util.Set;

/**
 * Created by Beata Iłowiecka on 21.08.2016.
 */
@Component
public class MailingFacadeImpl implements MailingFacade {

    @Override
    public void sendDocumentPublishedEmails(Document document, Set<EmployeeDetails> employeeDetails) {
        for (EmployeeDetails employeeDetail : employeeDetails) {
            String msg = String.format("Sending info to employee %s %s about printing document %s",
                    employeeDetail.getFirstName(), employeeDetail.getLastName(), document.title());
            Logger.getLogger(PrintSystemFacade.class).info(msg);
        }
    }
}
