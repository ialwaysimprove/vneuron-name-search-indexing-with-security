package nameIndexerSearchTool.repository.postgresql;

import nameIndexerSearchTool.models.CWJTable;
import nameIndexerSearchTool.models.JoinTableID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CWJTableRepository extends JpaRepository<CWJTable, JoinTableID> {
}
