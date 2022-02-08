package codegym.repository;

import codegym.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;


public interface IEmployeeRepo extends PagingAndSortingRepository<Employee, Long> {
    @Query(value = "select e from Employee e where e.name like concat('%',:name, '%')")
    ArrayList<Employee> findAllByName (@Param("name")String name);
}
