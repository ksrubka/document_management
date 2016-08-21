package pl.com.bottega.documentmanagement.api;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.documentmanagement.api.facades.HRSystemFacade;
import pl.com.bottega.documentmanagement.api.facades.MailingFacade;
import pl.com.bottega.documentmanagement.api.facades.PrintSystemFacade;
import pl.com.bottega.documentmanagement.domain.*;
import pl.com.bottega.documentmanagement.domain.repositories.DocumentRepository;
import pl.com.bottega.documentmanagement.domain.repositories.EmployeeRepository;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class DocumentFlowProcess {

    private DocumentFactory documentFactory;
    private DocumentRepository documentRepository;
    private EmployeeRepository employeeRepository;
    private UserManager userManager;
    private PrintCostCalculator printCostCalculator;
    private HRSystemFacade hrSystemFacade;
    private PrintSystemFacade printSystemFacade;
    private MailingFacade mailingFacade;

    public DocumentFlowProcess(DocumentRepository documentRepository, UserManager userManager,
                               DocumentFactory documentFactory, EmployeeRepository employeeRepository,
                               PrintCostCalculator printCostCalculator,
                               HRSystemFacade hrSystemFacade, MailingFacade mailingFacade,
                               PrintSystemFacade printSystemFacade) {
        this.documentRepository = documentRepository;
        this.userManager = userManager;
        this.documentFactory = documentFactory;
        this.employeeRepository = employeeRepository;
        this.printCostCalculator = printCostCalculator;
        this.hrSystemFacade = hrSystemFacade;
        this.printSystemFacade = printSystemFacade;
        this.mailingFacade = mailingFacade;
    }

    @Transactional
    @RequiresAuth(roles = "EDITOR")
    public DocumentNumber create(String title, String content) {
        checkNotNull(title);
        checkNotNull(content);

        Document document = documentFactory.create(title, content);
        documentRepository.save(document);
        return document.number();
    }

    @Transactional
    @RequiresAuth(roles = "EDITOR")
    public void change(DocumentNumber documentNumber, String newTitle, String newContent) {
        checkNotNull(documentNumber);
        checkNotNull(newTitle);
        checkNotNull(newContent);

        Document document = documentRepository.load(documentNumber);
        document.change(newTitle, newContent, printCostCalculator);
        documentRepository.save(document);
    }

    @Transactional
    @RequiresAuth(roles = "MANAGER")
    public void verify(DocumentNumber documentNumber) {
        checkNotNull(documentNumber);

        Document document = documentRepository.load(documentNumber);
        document.verify(userManager.currentEmployee());
        documentRepository.save(document);
    }

    @Transactional
    @RequiresAuth(roles = "MANAGER")
    public void publish(DocumentNumber documentNumber, Iterable<EmployeeId> ids) {
        checkNotNull(documentNumber);
        Document document = documentRepository.load(documentNumber);
        document.publish(userManager.currentEmployee(), getEmployees(ids));
        Set<EmployeeDetails> employeeDetailsSet = hrSystemFacade.getEmployeeDetails(Sets.newHashSet(ids));
        sendEmailAboutPublishedDocument(document, employeeDetailsSet);
        printDocument(document, employeeDetailsSet);
    }

    private void sendEmailAboutPublishedDocument(Document document, Set<EmployeeDetails> employeeDetails) {
        Set<EmployeeDetails> employeesHavingEmail = getEmployeesHavingEmail(employeeDetails);
        mailingFacade.sendDocumentPublishedEmails(document,employeesHavingEmail);
    }

    private Set<EmployeeDetails> getEmployeesHavingEmail(Set<EmployeeDetails> employeeDetails) {
        Set<EmployeeDetails> employeesHavingEmail = new HashSet<>();
        for (EmployeeDetails details : employeeDetails){
            if (details.hasEmail())
                employeesHavingEmail.add(details);
        }
        return employeesHavingEmail;
    }

    private void printDocument(Document document, Set<EmployeeDetails> employeeDetails) {
        Set<EmployeeDetails> employeesWithoutEmail = getEmployeesWithoutEmail(employeeDetails);
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

    private Set<Employee> getEmployees(Iterable<EmployeeId> ids) {
        Set<Employee> employees = employeeRepository.findByEmployeeIds(ids);
        ids.forEach((id) -> {
            if (!employees.stream().anyMatch((employee) -> employee.employeeId().equals(id)))
                employees.add(new Employee(id));
        });
        return employees;
    }

    @Transactional
    @RequiresAuth(roles = "MANAGER")
    public void archive(DocumentNumber documentNumber) {
        checkNotNull(documentNumber);
        Document document = documentRepository.load(documentNumber);
        document.delete(userManager.currentEmployee());
        documentRepository.save(document);
    }

}
