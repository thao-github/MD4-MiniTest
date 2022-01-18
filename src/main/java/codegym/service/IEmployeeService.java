package codegym.service;

import codegym.model.Employee;

import java.util.List;


public interface IEmployeeService {
    List<Employee> findAll();

    void save(Employee employee);

    Employee findById(long id);

    void delete(long id);
}
