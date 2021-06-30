package nameIndexerSearchTool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import nameIndexerSearchTool.services.SearchService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UIController {



private  SearchService searchService;
@Autowired
	public UIController(SearchService searchService) {
	    this.searchService = searchService;
	}

}
