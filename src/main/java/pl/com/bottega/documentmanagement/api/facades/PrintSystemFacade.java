package pl.com.bottega.documentmanagement.api.facades;

import pl.com.bottega.documentmanagement.api.EmployeeDetails;
import pl.com.bottega.documentmanagement.domain.Document;

import java.util.Set;

/**
 * Created by Beata Iłowiecka on 21.08.2016.
 */
public interface PrintSystemFacade {

    void printDocument(Document document, Set<EmployeeDetails> employeeDetails);
}
