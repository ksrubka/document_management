package pl.com.bottega.documentmanagement.api.facades.implementations;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import pl.com.bottega.documentmanagement.api.EmployeeDetails;
import pl.com.bottega.documentmanagement.api.facades.PrintSystemFacade;
import pl.com.bottega.documentmanagement.domain.Document;

import java.util.Set;

/**
 * Created by Beata Iłowiecka on 21.08.2016.
 */
@Component
public class PrintSystemFacadeImpl implements PrintSystemFacade {

    @Override
    public void printDocument(Document document, Set<EmployeeDetails> employeeDetails) {
        for (EmployeeDetails employeeDetail : employeeDetails) {
            String msg = String.format("Printing document %s for employee %s %s",
            document.title(), employeeDetail.getFirstName(), employeeDetail.getLastName());
            Logger.getLogger(PrintSystemFacade.class).info(msg);
        }
    }
}
