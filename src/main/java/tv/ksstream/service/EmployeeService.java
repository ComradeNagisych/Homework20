package tv.ksstream.service;

import tv.ksstream.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee add(String firstName, String lastName, int salary, int department);
    void remove(String firstName, String lastName, int salary, int department);
    Employee find(String firstName, String lastname, int salary, int department);
    Collection<Employee> findAll();
}
