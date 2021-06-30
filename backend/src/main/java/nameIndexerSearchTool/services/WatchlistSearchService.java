package nameIndexerSearchTool.services;

import lombok.extern.slf4j.Slf4j;
import nameIndexerSearchTool.models.Watchlist;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class WatchlistSearchService {

	private static final String WATCHLIST_INDEX = "watchlist-index";

	private ElasticsearchOperations elasticsearchOperations;

	@Autowired
	public WatchlistSearchService(final ElasticsearchOperations elasticsearchOperations) {
		super();
		this.elasticsearchOperations = elasticsearchOperations;
	}

	public SearchHits<Watchlist> processWatchlistSearch(String wholename) {

		log.info("Search with query {}", wholename);
		List<String> shouldQueryList = new ArrayList<>();
		shouldQueryList.add("        { \"match\": {\"whole_names.clean\": {\"query\": \"" + wholename + "\", " +
				"\"fuzziness\": \"AUTO\""
				+ ", \"boost\": 7"
				+"}} }");
		shouldQueryList.add("\n        { \"match\": {\"whole_names.clean_with_shingles\": {\"query\": \"" + wholename +
				"\", " +
				"\"fuzziness\": \"AUTO\""
				+ ", \"boost\": 4"
				+"}} }");
		shouldQueryList.add("\n        { \"multi_match\": {\n" +
				"      \"query\": \"" + wholename + "\",\n" +
				"      \"fields\": [\"whole_names.cross_lingual_double_metaphone^17\", \"whole_names.cross_lingual_double_metaphone_with_shingles^7\", \"whole_names.cross_lingual_beier_morse^7\", \"whole_names.cross_lingual_beier_morse_with_shingles^3\", \"whole_names.phonetic_dm^7\", \"whole_names.phonetic_dm_with_shingles^4\", \"whole_names.phonetic_bm^4\", \"whole_names.phonetic_bm_with_shingles^2\"]\n" +
				"        } } ");

		shouldQueryList.add("\n        { \"match\": { \"whole_names.truncated\": \""+ wholename +"\" }}");

		String shouldQuery = shouldQueryList.stream().collect(Collectors.joining(","));

		String theQueryAsAString = "{\"bool\" : {\"should\" : [\n" +
				shouldQuery +
				"      ]\n" +
				"    }\n" +
				"}";

		System.out.println(theQueryAsAString);
		StringQuery query = new StringQuery(theQueryAsAString);

		// 2. Execute search
		System.out.println(query.getSource());
		SearchHits<Watchlist> person = elasticsearchOperations.search(query, Watchlist.class);
		return person;
	}

	public SearchHits<Watchlist> runQuery(String wholename) {
		List<String> shouldQueryList = new ArrayList<>();
		shouldQueryList.add("        { \"match\": {\"whole_names.clean\": {\"query\": \"" + wholename + "\", " +
				"\"fuzziness\": \"AUTO\""
				+ ", \"boost\": 7"
				+"}} }");
		shouldQueryList.add("\n        { \"match\": {\"whole_names.clean_with_shingles\": {\"query\": \"" + wholename + "\", " +
				"\"fuzziness\": \"AUTO\""
				+ ", \"boost\": 4"
				+"}} }");
		shouldQueryList.add("\n        { \"match\": {\"cross_lingual_double_metaphone\": {\"query\": \"" + wholename
				+ "\", \"boost\": 17"
				+"}} }");
		shouldQueryList.add("\n        { \"match\": {\"whole_names.cross_lingual_double_metaphone_with_shingles\": {\"query\": \"" + wholename
				+ "\", \"boost\": 7"
				+"}} }");
		shouldQueryList.add("\n        { \"match\": {\"whole_names.cross_lingual_beier_morse\": {\"query\": \"" + wholename
				+ "\", \"boost\": 7"
				+"}} }");
		shouldQueryList.add("\n        { \"match\": {\"whole_names.cross_lingual_beier_morse_with_shingles\": {\"query\": \"" + wholename
				+ "\", \"boost\": 3"
				+"}} }");
		shouldQueryList.add("\n        { \"match\": {\"whole_names.phonetic_dm\": {\"query\": \"" + wholename
				+ "\", \"boost\": 7"
				+"}} }");
		shouldQueryList.add("\n        { \"match\": {\"whole_names.phonetic_dm_with_shingles\": {\"query\": \"" + wholename
				+ "\", \"boost\": 4"
				+"}} }");
		shouldQueryList.add("\n        { \"match\": {\"whole_names.phonetic_bm\": {\"query\": \"" + wholename
				+ "\", \"boost\": 4"
				+"}} }");
		shouldQueryList.add("\n        { \"match\": {\"whole_names.phonetic_bm_with_shingles\": {\"query\": \"" + wholename
				+ "\", \"boost\": 2"
				+"}} }");

		shouldQueryList.add("\n        { \"match\": { \"whole_names.truncated\": \""+ wholename +"\" }}");


		String shouldQuery = shouldQueryList.stream().collect(Collectors.joining(","));
		String theQueryAsAString = "{\"bool\" : {\"should\" : [\n" +
				shouldQuery +
				"      ]\n" +
				"    }\n" +
				"}";
		System.out.println(theQueryAsAString);

		StringQuery query = new StringQuery(theQueryAsAString);

		System.out.println(query.getSource());
		SearchHits<Watchlist> person = elasticsearchOperations.search(query, Watchlist.class);
		return person;
	}
}
