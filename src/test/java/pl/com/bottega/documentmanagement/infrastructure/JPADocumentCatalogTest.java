package pl.com.bottega.documentmanagement.infrastructure;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PERSIST_STORE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.documentmanagement.api.DocumentCriteria;
import pl.com.bottega.documentmanagement.api.DocumentDto;
import pl.com.bottega.documentmanagement.api.DocumentSearchResults;
import pl.com.bottega.documentmanagement.api.DocumentsCatalog;
import pl.com.bottega.documentmanagement.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration({"/application.xml", "/mock-auth-context.xml"})
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
@WebAppConfiguration
public class JPADocumentCatalogTest {

    private final static long PAGE_NUMBER = 1L;
    private final static long DOCUMENTS_PER_PAGE = 25L;

    @Autowired
    private DocumentsCatalog jpaDocumentsCatalog;

    @Autowired
    private PrintCostCalculator printCostCalculator;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void shouldFindDocumentByStatus() {
        //given
        Employee employee = new Employee("test", "test", new EmployeeId(10L));
        Document documentDraft = new Document(new DocumentNumber("1"), "draft", "draft", employee, printCostCalculator);
        Document documentVerified = new Document(new DocumentNumber("2"), "verified", "verified", employee, printCostCalculator);
        entityManager.persist(employee);
        entityManager.persist(documentDraft);
        documentVerified.verify(employee);
        entityManager.persist(documentVerified);

        //when
        DocumentCriteria documentCriteria = new DocumentCriteria();
        documentCriteria.setPageNumber(PAGE_NUMBER);
        documentCriteria.setPerPage(DOCUMENTS_PER_PAGE);
        documentCriteria.setStatus(DocumentStatus.DRAFT);
        DocumentSearchResults results = jpaDocumentsCatalog.find(documentCriteria);

        //then
        assertEquals(new Long(1), results.getTotalPages());
        List<DocumentDto> documents = Lists.newArrayList(results.getDocuments());
        assertEquals(1, documents.size());
        DocumentDto document = documents.get(0);
        assertEquals("draft", document.getContent());
        assertEquals("DRAFT", document.getStatus());
    }


    @Sql("/fixtures/documents.sql")
    @Test
    @Transactional
    public void shouldFindDocumentByDraftStatusSQLSetup() {
        //given

        //when
        DocumentCriteria documentCriteria = new DocumentCriteria();
        documentCriteria.setPageNumber(PAGE_NUMBER);
        documentCriteria.setPerPage(DOCUMENTS_PER_PAGE);
        documentCriteria.setStatus(DocumentStatus.DRAFT);
        DocumentSearchResults results = jpaDocumentsCatalog.find(documentCriteria);

        //then
        assertEquals(new Long(1), results.getTotalPages());
        List<DocumentDto> documents = Lists.newArrayList(results.getDocuments());
        assertEquals(3, documents.size());
        DocumentDto document = documents.get(0);
        assertEquals("draft content", document.getContent());
        assertEquals("DRAFT", document.getStatus());
        document = documents.get(1);
        assertEquals("second draft content", document.getContent());
        assertEquals("DRAFT", document.getStatus());
        document = documents.get(2);
        assertEquals("third draft content", document.getContent());
        assertEquals("DRAFT", document.getStatus());
    }

    @Sql("/fixtures/documents.sql")
    @Test
    @Transactional
    public void shouldFindDocumentByVerifiedStatusSQLSetup() {
        //given

        //when
        DocumentCriteria documentCriteria = new DocumentCriteria();
        documentCriteria.setPageNumber(PAGE_NUMBER);
        documentCriteria.setPerPage(DOCUMENTS_PER_PAGE);
        documentCriteria.setStatus(DocumentStatus.VERIFIED);
        DocumentSearchResults results = jpaDocumentsCatalog.find(documentCriteria);

        //then
        assertEquals(new Long(1), results.getTotalPages());
        List<DocumentDto> documents = Lists.newArrayList(results.getDocuments());
        assertEquals(2, documents.size());
        DocumentDto document = documents.get(0);
        assertEquals("verified content", document.getContent());
        assertEquals("VERIFIED", document.getStatus());
        document = documents.get(1);
        assertEquals("second verified content", document.getContent());
        assertEquals("VERIFIED", document.getStatus());
    }

    @Sql("/fixtures/documents.sql")
    @Test
    @Transactional
    public void shouldFindDocumentByCreateFromDateSQLSetup() throws ParseException {
        //given
        DateFormat dateFormat = DateFormat.getDateInstance();

        //when
        DocumentCriteria documentCriteria = new DocumentCriteria();
        documentCriteria.setPageNumber(PAGE_NUMBER);
        documentCriteria.setPerPage(DOCUMENTS_PER_PAGE);
        documentCriteria.setCreatedFrom(dateFormat.parse("2016-02-01"));
        DocumentSearchResults results = jpaDocumentsCatalog.find(documentCriteria);

        //then
        assertEquals(new Long(1), results.getTotalPages());
        List<DocumentDto> documents = Lists.newArrayList(results.getDocuments());
        assertEquals(3, documents.size());
        DocumentDto document = documents.get(0);
        assertEquals("second draft content", document.getContent());
        document = documents.get(1);
        assertEquals("second verified content", document.getContent());
        document = documents.get(2);
        assertEquals("third draft content", document.getContent());
    }

    @Sql("/fixtures/documents.sql")
    @Test
    @Transactional
    public void shouldFindDocumentByCreateUntilDateSQLSetup() throws ParseException {
        //given
        DateFormat dateFormat = DateFormat.getDateInstance();

        //when
        DocumentCriteria documentCriteria = new DocumentCriteria();
        documentCriteria.setPageNumber(PAGE_NUMBER);
        documentCriteria.setPerPage(DOCUMENTS_PER_PAGE);
        documentCriteria.setCreatedUntil(dateFormat.parse("2016-02-01"));
        DocumentSearchResults results = jpaDocumentsCatalog.find(documentCriteria);

        //then
        assertEquals(new Long(1), results.getTotalPages());
        List<DocumentDto> documents = Lists.newArrayList(results.getDocuments());
        assertEquals(2, documents.size());
        DocumentDto document = documents.get(0);
        assertEquals("draft content", document.getContent());
        document = documents.get(1);
        assertEquals("verified content", document.getContent());
    }

    @Sql("/fixtures/documents.sql")
    @Test
    @Transactional
    public void shouldFindDocumentByCreateDatesDateSQLSetup() throws ParseException {
        //given
        DateFormat dateFormat = DateFormat.getDateInstance();

        //when
        DocumentCriteria documentCriteria = new DocumentCriteria();
        documentCriteria.setPageNumber(PAGE_NUMBER);
        documentCriteria.setPerPage(DOCUMENTS_PER_PAGE);
        documentCriteria.setCreatedFrom(dateFormat.parse("2016-02-01"));
        documentCriteria.setCreatedUntil(dateFormat.parse("2016-03-01"));
        DocumentSearchResults results = jpaDocumentsCatalog.find(documentCriteria);

        //then
        assertEquals(new Long(1), results.getTotalPages());
        List<DocumentDto> documents = Lists.newArrayList(results.getDocuments());
        assertEquals(3, documents.size());
        DocumentDto document = documents.get(0);
        assertEquals("second draft content", document.getContent());
        document = documents.get(1);
        assertEquals("second verified content", document.getContent());
        document = documents.get(2);
        assertEquals("third draft content", document.getContent());
    }

    @Sql("/fixtures/documents.sql")
    @Test
    @Transactional
    public void shouldFindDocumentByVerifiedFromDateSQLSetup() throws ParseException {
        //given
        DateFormat dateFormat = DateFormat.getDateInstance();

        //when
        DocumentCriteria documentCriteria = new DocumentCriteria();
        documentCriteria.setPageNumber(PAGE_NUMBER);
        documentCriteria.setPerPage(DOCUMENTS_PER_PAGE);
        documentCriteria.setVerifiedFrom(dateFormat.parse("2016-02-01"));
        DocumentSearchResults results = jpaDocumentsCatalog.find(documentCriteria);

        //then
        assertEquals(new Long(1), results.getTotalPages());
        List<DocumentDto> documents = Lists.newArrayList(results.getDocuments());
        assertEquals(1, documents.size());
        DocumentDto document = documents.get(0);
        assertEquals("second verified content", document.getContent());
    }

    @Sql("/fixtures/documents.sql")
    @Test
    @Transactional
    public void shouldFindDocumentByVerifiedUntilDateSQLSetup() throws ParseException {
        //given
        DateFormat dateFormat = DateFormat.getDateInstance();

        //when
        DocumentCriteria documentCriteria = new DocumentCriteria();
        documentCriteria.setPageNumber(PAGE_NUMBER);
        documentCriteria.setPerPage(DOCUMENTS_PER_PAGE);
        documentCriteria.setVerifiedUntil(dateFormat.parse("2016-02-01"));
        DocumentSearchResults results = jpaDocumentsCatalog.find(documentCriteria);

        //then
        assertEquals(new Long(1), results.getTotalPages());
        List<DocumentDto> documents = Lists.newArrayList(results.getDocuments());
        assertEquals(1, documents.size());
        DocumentDto document = documents.get(0);
        assertEquals("verified content", document.getContent());
    }

    @Sql("/fixtures/documents.sql")
    @Test
    @Transactional
    public void shouldFindDocumentByVerifyDatesSQLSetup() throws ParseException {
        //given
        DateFormat dateFormat = DateFormat.getDateInstance();

        //when
        DocumentCriteria documentCriteria = new DocumentCriteria();
        documentCriteria.setPageNumber(PAGE_NUMBER);
        documentCriteria.setPerPage(DOCUMENTS_PER_PAGE);
        documentCriteria.setVerifiedFrom(dateFormat.parse("2016-01-01"));
        documentCriteria.setVerifiedUntil(dateFormat.parse("2016-01-04"));
        DocumentSearchResults results = jpaDocumentsCatalog.find(documentCriteria);

        //then
        assertEquals(new Long(1), results.getTotalPages());
        List<DocumentDto> documents = Lists.newArrayList(results.getDocuments());
        assertEquals(1, documents.size());
        DocumentDto document = documents.get(0);
        assertEquals("verified content", document.getContent());
    }

    @Sql("/fixtures/documents.sql")
    @Test
    @Transactional
    public void shouldFindDocumentCreatedBydSQLSetup() {
        //given

        //when
        DocumentCriteria documentCriteria = new DocumentCriteria();
        documentCriteria.setPageNumber(PAGE_NUMBER);
        documentCriteria.setPerPage(DOCUMENTS_PER_PAGE);
        documentCriteria.setCreatedBy(100l);
        DocumentSearchResults results = jpaDocumentsCatalog.find(documentCriteria);

        //then
        assertEquals(new Long(1), results.getTotalPages());
        List<DocumentDto> documents = Lists.newArrayList(results.getDocuments());
        assertEquals(2, documents.size());
        DocumentDto document = documents.get(0);
        assertEquals("draft content", document.getContent());
        document = documents.get(1);
        assertEquals("second verified content", document.getContent());
    }

    @Sql("/fixtures/documents.sql")
    @Test
    @Transactional
    public void shouldFindDocumentVerifiedBydSQLSetup() {
        //given

        //when
        DocumentCriteria documentCriteria = new DocumentCriteria();
        documentCriteria.setPageNumber(PAGE_NUMBER);
        documentCriteria.setPerPage(DOCUMENTS_PER_PAGE);
        documentCriteria.setVerifiedBy(200l);
        DocumentSearchResults results = jpaDocumentsCatalog.find(documentCriteria);

        //then
        assertEquals(new Long(1), results.getTotalPages());
        List<DocumentDto> documents = Lists.newArrayList(results.getDocuments());
        assertEquals(1, documents.size());
        DocumentDto document = documents.get(0);
        assertEquals("verified content", document.getContent());
        assertEquals("VERIFIED", document.getStatus());
    }

    @Sql("/fixtures/documents.sql")
    @Test
    @Transactional
    public void shouldFindDocumentByQuerySQLSetup() {
        //given

        //when
        DocumentCriteria documentCriteria = new DocumentCriteria();
        documentCriteria.setPageNumber(PAGE_NUMBER);
        documentCriteria.setPerPage(DOCUMENTS_PER_PAGE);
        documentCriteria.setQuery("second");
        DocumentSearchResults results = jpaDocumentsCatalog.find(documentCriteria);

        //then
        assertEquals(new Long(1), results.getTotalPages());
        List<DocumentDto> documents = Lists.newArrayList(results.getDocuments());
        assertEquals(2, documents.size());
        DocumentDto document = documents.get(0);
        assertEquals("second draft content", document.getContent());
        document = documents.get(1);
        assertEquals("second verified content", document.getContent());
    }

    @Sql("/fixtures/documents.sql")
    @Test
    @Transactional
    public void shouldFindDocumentByPartialQuerySQLSetup() {
        //given

        //when
        DocumentCriteria documentCriteria = new DocumentCriteria();
        documentCriteria.setPageNumber(PAGE_NUMBER);
        documentCriteria.setPerPage(DOCUMENTS_PER_PAGE);
        documentCriteria.setQuery("draf");
        DocumentSearchResults results = jpaDocumentsCatalog.find(documentCriteria);

        //then
        assertEquals(new Long(1), results.getTotalPages());
        List<DocumentDto> documents = Lists.newArrayList(results.getDocuments());
        assertEquals(3, documents.size());
        DocumentDto document = documents.get(0);
        assertEquals("draft content", document.getContent());
        document = documents.get(1);
        assertEquals("second draft content", document.getContent());
        document = documents.get(2);
        assertEquals("third draft content", document.getContent());
    }

    @Sql("/fixtures/documents.sql")
    @Test
    @Transactional
    public void shouldGetDocumentSQLSetup() {
        //given

        //when
        DocumentDto document = jpaDocumentsCatalog.get(new DocumentNumber("13A"));

        //then
        assertNotNull(document);
        assertEquals("verified content", document.getContent());
        assertEquals("VERIFIED", document.getStatus());
    }
}
