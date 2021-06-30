package nameIndexerSearchTool.repository.elasticsearch;

import nameIndexerSearchTool.models.Watchlist;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistRepository extends ElasticsearchRepository<Watchlist, String> {}
