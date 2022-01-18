package codegym.repository;

import codegym.model.Branch;
import org.springframework.data.repository.CrudRepository;

public interface IBranchRepo extends CrudRepository<Branch, Long> {
}
