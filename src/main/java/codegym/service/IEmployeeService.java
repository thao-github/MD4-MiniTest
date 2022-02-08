package codegym.service;

import codegym.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IEmployeeService {
    List<Employee>findAll();

    Page<Employee> findAll(Pageable pageable);

    List<Employee> findAllByName(String name);

    void save(Employee employee);

    Employee findById(long id);

    void delete(long id);


}
