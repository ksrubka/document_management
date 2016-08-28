package pl.com.bottega.documentmanagement.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.documentmanagement.api.EmployeeDetails;
import pl.com.bottega.documentmanagement.api.facades.HRSystemFacade;
import pl.com.bottega.documentmanagement.domain.Employee;
import pl.com.bottega.documentmanagement.domain.EmployeeId;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Beata Iłowiecka on 21.08.2016.
 */
@Component
public class HRSystemFacadeImpl implements HRSystemFacade {

    @Override
    public Set<EmployeeDetails> getEmployeeDetails(Set<EmployeeId> employees) {
        /*Set<EmployeeDetails> employeeDetailsSet = new HashSet<>();
        for (Employee employee : employees) {
            EmployeeDetails employeeDetails = new EmployeeDetails();
            employeeDetails.setFirstName("Janina");
            employeeDetails.setLastName("Nowak");
            employeeDetails.setAddress("Księżycoa 2");
            employeeDetails.setEmail("janina.nowak@wp.pl");
            employeeDetailsSet.add(employeeDetails);
        }
        return employeeDetailsSet;*/
        return employees.stream().map(employee -> {
            EmployeeDetails employeeDetails = new EmployeeDetails();
            employeeDetails.setFirstName("Janina");
            employeeDetails.setLastName("Nowak");
            employeeDetails.setAddress("Księżycowa 2");
            employeeDetails.setEmail("janina.nowak@wp.pl");
            return employeeDetails;
        }).collect(Collectors.toSet());
    }
}
