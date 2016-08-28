package pl.com.bottega.documentmanagement.api.events;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;
import pl.com.bottega.documentmanagement.api.EmployeeDetails;
import pl.com.bottega.documentmanagement.api.facades.HRSystemFacade;
import pl.com.bottega.documentmanagement.api.facades.PrintSystemFacade;
import pl.com.bottega.documentmanagement.domain.Document;
import pl.com.bottega.documentmanagement.domain.EmployeeId;
import pl.com.bottega.documentmanagement.domain.Reader;
import pl.com.bottega.documentmanagement.domain.events.DocumentListener;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Beata Iłowiecka on 27.08.2016.
 */
@Component
public class DocumentPrinter implements DocumentListener {

    private PrintSystemFacade printSystemFacade;
    private HRSystemFacade hrSystemFacade;

    public DocumentPrinter(PrintSystemFacade printSystemFacade, HRSystemFacade hrSystemFacade) {
        this.printSystemFacade = printSystemFacade;
        this.hrSystemFacade = hrSystemFacade;
    }

    @Override
    public void published(Document document) {
        Set<Reader> readers = document.readers();
        //Set<EmployeeId> ids = readers.stream().map(reader -> reader.employeeId()).collect(Collectors.toSet());
        Set<EmployeeId> ids = new HashSet<>();
        for (Reader reader : readers) {
            ids.add(reader.employeeId());
        }
        Set<EmployeeDetails> employeeDetailsSet = hrSystemFacade.getEmployeeDetails(Sets.newHashSet(ids));
        printDocument(document, employeeDetailsSet);
    }

    private void printDocument(Document document, Set<EmployeeDetails> employeeDetailsSet) {
        Set<EmployeeDetails> employeesWithoutEmail = getEmployeesWithoutEmail(employeeDetailsSet);
        printSystemFacade.printDocument(document, employeesWithoutEmail);
    }

    private Set<EmployeeDetails> getEmployeesWithoutEmail(Set<EmployeeDetails> employeeDetails) {
        Set<EmployeeDetails> employeesWithoutEmail = new HashSet<>();
        for (EmployeeDetails details : employeeDetails){
            if (!details.hasEmail())
                employeesWithoutEmail.add(details);
        }
        return employeesWithoutEmail;
    }
}
