package codegym.repository;

import codegym.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface IEmployeeRepo extends CrudRepository<Employee, Long> {
}
