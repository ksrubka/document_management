package pl.com.bottega.documentmanagement.api.facades;

import pl.com.bottega.documentmanagement.api.EmployeeDetails;
import pl.com.bottega.documentmanagement.domain.Employee;
import pl.com.bottega.documentmanagement.domain.EmployeeId;

import java.util.Set;

/**
 * Created by Beata Iłowiecka on 21.08.2016.
 */
public interface HRSystemFacade {

    Set<EmployeeDetails> getEmployeeDetails(Set<EmployeeId> employees);
}
