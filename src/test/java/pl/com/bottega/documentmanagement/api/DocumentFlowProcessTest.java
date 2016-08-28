package pl.com.bottega.documentmanagement.api;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.documentmanagement.api.facades.HRSystemFacade;
import pl.com.bottega.documentmanagement.api.facades.MailingFacade;
import pl.com.bottega.documentmanagement.api.facades.PrintSystemFacade;
import pl.com.bottega.documentmanagement.domain.*;
import pl.com.bottega.documentmanagement.domain.repositories.DocumentRepository;
import pl.com.bottega.documentmanagement.domain.repositories.EmployeeRepository;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by maciuch on 17.08.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class DocumentFlowProcessTest {

    private DocumentFlowProcess documentFlowProcess;

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private UserManager userManager;

    @Mock
    private DocumentNumberGenerator documentNumberGenerator;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DocumentFactory documentFactory;

    private String anyTitle = "xxxx";

    private String anyContent = "cccc";

    @Mock
    private DocumentNumber documentNumber;

    @Mock
    private Document document;

    @Mock
    private Employee employee;

    @Autowired
    private PrintCostCalculator printCostCalculator;

    @Mock
    private PrintSystemFacade printSystemFacade;

    @Before
    public void setUp() throws Exception {
        documentFlowProcess = new DocumentFlowProcess(documentRepository, userManager, documentFactory,
                employeeRepository, printCostCalculator, printSystemFacade);
    }

    @Test
    @Transactional
    public void shouldCreateDocument() {
        //given
        when(documentFactory.create(anyTitle, anyContent)).thenReturn(document);
        when(document.number()).thenReturn(documentNumber);

        //when
        DocumentNumber nr = documentFlowProcess.create(anyTitle, anyContent);

        //then
        verify(documentRepository).save(document);
        assertEquals(nr, documentNumber);
    }

    @Test
    @Transactional
    public void shouldUpdateDocument() {
        //given
        when(documentRepository.load(documentNumber)).thenReturn(document);

        //when
        documentFlowProcess.change(documentNumber, anyTitle, anyContent);

        //then
        verify(document).change(anyTitle, anyContent, printCostCalculator);
        verify(documentRepository).save(document);
    }

    @Test
    @Transactional
    public void shouldVerifyDocument() {
        //given
        when(documentRepository.load(documentNumber)).thenReturn(document);
        when(userManager.currentEmployee()).thenReturn(employee);

        //when
        documentFlowProcess.verify(documentNumber);

        //then
        verify(document).verify(employee);
        verify(documentRepository).save(document);
    }

    @Test
    @Transactional
    public void shouldPublishDocument() {
        //given
        when(documentRepository.load(documentNumber)).thenReturn(document);
        Set<EmployeeId> employeeIds = Sets.newHashSet(1L, 2L, 3L).stream().map(EmployeeId::new).collect(Collectors.toSet());
        when(employeeRepository.findByEmployeeIds(employeeIds)).thenReturn(Sets.newHashSet());
        when(userManager.currentEmployee()).thenReturn(employee);

        //when
        documentFlowProcess.publish(documentNumber, employeeIds);

        //then
        verify(document).publish(employee, employeeIds.stream().map(Employee::new).collect(Collectors.toSet()));
    }

    @Test
    @Transactional
    public void shouldArchiveDocument() {
        //given
        when(documentRepository.load(documentNumber)).thenReturn(document);
        when(userManager.currentEmployee()).thenReturn(employee);

        //when
        documentFlowProcess.archive(documentNumber);

        //then
        verify(document).delete(employee);
        verify(documentRepository).save(document);
    }

    @Test
    public void shouldSendPrintREquestForEmployeesWithoutEmail() {

    }

    @Test
    public void shouldSendEmailsToEmployeesWithEmail() {
        //zamockuj fasadę HR by zwracała odpowiednich pracowników dla danego testu
    }
}
