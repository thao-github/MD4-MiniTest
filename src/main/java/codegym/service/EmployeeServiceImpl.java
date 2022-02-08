package codegym.service;

import codegym.model.Employee;
import codegym.repository.IEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class EmployeeServiceImpl implements IEmployeeService{

    @Autowired
    IEmployeeRepo employeeRepo;

    @Override
    public List<Employee> findAll() {
        return (List<Employee>) employeeRepo.findAll();
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepo.findAll(pageable);
    }

    @Override
    public List<Employee> findAllByName(String name) {
        return  employeeRepo.findAllByName(name);
    }

    @Override
    public void save(Employee employee) {
        employeeRepo.save(employee);
    }

    @Override
    public Employee findById(long id) {
        return employeeRepo.findById(id).get();
    }

    @Override
    public void delete(long id) {
        employeeRepo.deleteById(id);
    }

}
