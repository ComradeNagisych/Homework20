package tv.ksstream.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tv.ksstream.exception.EmployeeAlreadyExistsException;
import tv.ksstream.exception.EmployeeNotFoundException;
import tv.ksstream.exception.InvalidNameException;
import tv.ksstream.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class MapBasedEmployeeService implements EmployeeService {
    private final Map<String, Employee> storage = new HashMap<>();

    @Override
    public Employee add(String firstName, String lastName, int salary, int department) {
        validateNames(firstName, lastName);
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), salary, department);
        if (storage.containsKey(firstName + lastName + salary + department)) {
            throw new EmployeeAlreadyExistsException("Employee already exists, addition impossible");
        }
        storage.put(firstName + lastName + salary + department, employee);
        return employee;
    }
    @Override
    public void remove(String firstName, String lastName, int salary, int department) {
        if (!storage.containsKey(firstName + lastName + salary + department)) {
            throw new EmployeeNotFoundException("Employee not found, removal impossible");
        }
        storage.remove(firstName + lastName + salary + department);
    }
    @Override
    public Employee find(String firstName, String lastName, int salary, int department) {
        if (!storage.containsKey(firstName + lastName + salary + department)) {
            throw new EmployeeNotFoundException("Employee not found, search stopped");
        }
        return storage.get(firstName + lastName + salary + department);
    }
    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(storage.values());
    }
    private void validateNames(String... names) {
        for (String name : names) {
            if (!StringUtils.isAlpha(name)) {
                throw new InvalidNameException("Name " + name + " is invalid");
            }
        }
    }
}
