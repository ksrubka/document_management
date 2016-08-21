package pl.com.bottega.documentmanagement.acceptance;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.documentmanagement.api.DocumentDto;
import pl.com.bottega.documentmanagement.api.DocumentFlowProcess;
import pl.com.bottega.documentmanagement.api.DocumentsCatalog;
import pl.com.bottega.documentmanagement.api.UserManager;
import pl.com.bottega.documentmanagement.domain.DocumentNumber;
import pl.com.bottega.documentmanagement.domain.EmployeeId;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static pl.com.bottega.documentmanagement.utils.Assert.assertDatesEqual;

/**
 * Created by Beata Iłowiecka on 20.08.2016.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration({"/application.xml"})
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
@WebAppConfiguration
public class DocumentFlowAcceptanceTest {

    @Autowired
    private UserManager userManager;

    @Autowired
    private DocumentFlowProcess documentFlowProcess;

    @Autowired
    private DocumentsCatalog documentsCatalog;

    private String anyLogin = "xxx";
    private String anyPassword = "yyy";
    private Long anyId = 1L;
    private EmployeeId anyEmployeeId = new EmployeeId(anyId);
    private String anyTitle = "test title";
    private String anyConent = "test content";

    @Before
    public void setUp() {
        userManager.createAdmin();
        userManager.login("admin", "admin");
    }


    @Test
    @Transactional
    public void shouldCreateDocument() {
        //given
        loginAs("STAFF", "EDITOR");

        //when
        DocumentNumber nr = documentFlowProcess.create(anyTitle, anyConent);

        //then
        DocumentDto documentDto = documentsCatalog.get(nr);
        assertEquals(anyConent, documentDto.getContent());
        assertEquals(anyTitle, documentDto.getTitle());
        assertEquals(nr.getNumber(), documentDto.getNumber());
        assertDatesEqual(new Date(), documentDto.getCreatedAt());
    }

    @Test
    @Transactional
    public void shouldVerifyDocument() {
        //given
        loginAs("STAFF", "EDITOR", "MANAGER");
        DocumentNumber nr = documentFlowProcess.create(anyTitle, anyConent);

        //when
        documentFlowProcess.verify(nr);

        //then
        DocumentDto documentDto = documentsCatalog.get(nr);
        assertEquals("VERIFIED", documentDto.getStatus());
        assertEquals(anyId, documentDto.getVerificatorId());
        assertDatesEqual(new Date(), documentDto.getVerificatedAt());
    }

    private void loginAs(String... roles) {
        userManager.signup(anyLogin, anyPassword, anyEmployeeId);
        userManager.updateRoles(anyEmployeeId, Sets.newHashSet(roles));
        userManager.login(anyLogin, anyPassword);
    }
}
