package nameIndexerSearchTool;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import nameIndexerSearchTool.models.CWJTable;
import nameIndexerSearchTool.models.Customer;
import nameIndexerSearchTool.models.Watchlist;
import nameIndexerSearchTool.repository.elasticsearch.WatchlistRepository;
import nameIndexerSearchTool.repository.postgresql.CWJTableRepository;
import nameIndexerSearchTool.repository.postgresql.CustomerRepository;
import nameIndexerSearchTool.services.CustomerSearch;
import nameIndexerSearchTool.services.parsers.ExportEUSCParserService;
import nameIndexerSearchTool.services.parsers.ExportUNSCParserService;
import nameIndexerSearchTool.services.parsers.ExportUSDTParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.net.InetAddress;
import java.io.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nameIndexerSearchTool.services.parsers.CustomersLoader;

@SpringBootApplication
@Slf4j
public class SearchToolBackendApplication {
	// If it works, then it was because this application can't handle constructor on main class*
	@Autowired
	private ElasticsearchOperations esOps;
	@Autowired
	private WatchlistRepository watchlistElasticRepo;
	@Autowired
	CustomerRepository postgresRepository;
	@Autowired
	CustomerSearch customerSearch;

	@Autowired
	CWJTableRepository cwjTableRepository;

	// private CustomerRepository customerPostgresRepo;

	private static void waitForElasticsearch() {
		boolean elasticIsAvailable = false;
		try {
			while (elasticIsAvailable){
				elasticIsAvailable = InetAddress.getByName("es01:9200").isReachable(10000);
				System.err.println("Waiting to connect to elastic server");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {

		// TimeUnit.MINUTES.sleep(1);
		log.info("We're waiting for Elasticsearch to Start");
		System.out.println("We're waiting for Elasticsearch to start");
		waitForElasticsearch();
		System.out.println("We're about to get Elasticsearch to Start");
		log.info("We're about to get Elasticsearch to Start");
		System.out.println("We have an updated Backend now!");

		TimeUnit.MINUTES.sleep(1);
		SpringApplication.run(SearchToolBackendApplication.class, args);
	}

	@PreDestroy
	public void deleteIndex() {
		// TimeUnit.MINUTES.sleep(1);
		if (esOps.indexOps(Watchlist.class).exists()) {
			esOps.indexOps(Watchlist.class).delete();
		}
		postgresRepository.deleteAll(); // Delete all data from postgres, sad thing is, I don't know how to delete the table itself! :'(
		// customerPostgresRepo.deleteAll();

	}

	@PostConstruct
	public void buildIndex() throws InterruptedException {
		if (esOps.indexOps(Watchlist.class).exists()) {
			esOps.indexOps(Watchlist.class).refresh();
			watchlistElasticRepo.deleteAll();
		}
		Collection<Watchlist> unscWatchlist = ExportUNSCParserService.prepareCustomersDataset();
		Collection<Watchlist> euscWatchlist = ExportEUSCParserService.prepareCustomersDataset();
		Collection<Watchlist> usdtWatchlist = ExportUSDTParserService.prepareCustomersDataset();
		Collection<Customer> vncustomers = CustomersLoader.prepareDataset();

		TimeUnit.MINUTES.sleep(1);

		watchlistElasticRepo.saveAll(unscWatchlist);
		watchlistElasticRepo.saveAll(euscWatchlist);
		watchlistElasticRepo.saveAll(usdtWatchlist);
		postgresRepository.saveAll(vncustomers);

		long start = System.currentTimeMillis();
		List<CWJTable> searchHits = customerSearch.checkCustomers(vncustomers);
		long end = System.currentTimeMillis();

		long elapsedTime = end - start;
		System.out.println("\nElapsed time is : " +  elapsedTime);


		cwjTableRepository.saveAll(searchHits);

		System.out.println("Everything is done right now!... Ready to serve search requests"); // Probably will be better if the files can be loaded and searched on, but searching is allowed at the same time?


	}

}
