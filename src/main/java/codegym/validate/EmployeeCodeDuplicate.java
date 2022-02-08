package codegym.validate;

import codegym.model.Employee;
import codegym.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class EmployeeCodeDuplicate implements Validator {
    @Autowired
    IEmployeeService employeeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;
        List<Employee> employees = employeeService.findAll();
        for (Employee e: employees) {
            if (e.getEmployeeCode().equals(employee.getEmployeeCode())){
                errors.rejectValue("employeeCode","employeeCode.duplicate");
            }
        }
    }
}
