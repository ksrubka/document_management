package pl.com.bottega.documentmanagement.domain;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reader {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Document document;

    @ManyToOne(cascade = CascadeType.ALL)
    private Employee employee;

    private boolean confirmed;

    @Temporal(TemporalType.TIMESTAMP)
    private Date confirmedAt;

    @ManyToOne
    private Employee confirmedBy;

    Reader() {}

    public Reader(Document document, Employee employee) {
        this.document = document;
        this.employee = employee;
    }

    public boolean confirmed() {
        return confirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return confirmed == reader.confirmed &&
                Objects.equal(document, reader.document) &&
                Objects.equal(employee, reader.employee) &&
                Objects.equal(confirmedBy, reader.confirmedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(document, employee, confirmed, confirmedBy);
    }

    public Date confirmedAt() {
        return confirmedAt;
    }

    public boolean concernsEmployee(Employee employee) {
        return this.employee.equals(employee);
    }

    void confirm() {
        confirmed = true;
        confirmedAt = new Date();
    }

    public Employee confirmedBy() {
        return confirmedBy;
    }

    public void confirm(Employee confirmator) {
        confirm();
        this.confirmedBy = confirmator;
    }

    public EmployeeId employeeId() {
        return employee.employeeId();
    }
}
