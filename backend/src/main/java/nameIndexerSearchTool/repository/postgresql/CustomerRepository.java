package nameIndexerSearchTool.repository.postgresql;

import nameIndexerSearchTool.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("nameIndexerSearchTool.repository.postgresql")
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}