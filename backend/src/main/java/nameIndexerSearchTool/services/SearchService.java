package nameIndexerSearchTool.services;

import nameIndexerSearchTool.repository.elasticsearch.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class SearchService {

	private WatchlistRepository WatchlistRepository;

	private  ElasticsearchOperations elasticsearchOperations;

	@Autowired
	public SearchService(WatchlistRepository WatchlistRepository, ElasticsearchOperations elasticsearchOperations) {
		this.WatchlistRepository = WatchlistRepository;
		this.elasticsearchOperations = elasticsearchOperations;
	}
}
