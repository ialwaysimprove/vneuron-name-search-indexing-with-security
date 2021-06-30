package nameIndexerSearchTool.controllers;

import nameIndexerSearchTool.infra.security.annotation.AllowedRoles;
import nameIndexerSearchTool.models.Watchlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.web.bind.annotation.*;

import nameIndexerSearchTool.services.WatchlistSearchService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/")
@Slf4j
public class SearchController {

	private WatchlistSearchService searchService;

	@Autowired
	public SearchController(WatchlistSearchService searchService) {
	    this.searchService = searchService;
	}

	@GetMapping("/watchlist/")
	@CrossOrigin
	@AllowedRoles("VISITOR")
	@ResponseBody
	public SearchHits<Watchlist> fetchWatchlistContaining(@RequestParam(value = "wholename", required = false) String query) {
		System.err.println("Method called");
		log.info("Searching for sanctioned entities {}",query);
		return searchService.processWatchlistSearch(query) ; //Just make into a search by whole fields... (multi field match)
	}

	public SearchHits<Watchlist> checkName(String namesList) {

		SearchHits<Watchlist> person = searchService.runQuery(namesList);
		return person;
	}

}
