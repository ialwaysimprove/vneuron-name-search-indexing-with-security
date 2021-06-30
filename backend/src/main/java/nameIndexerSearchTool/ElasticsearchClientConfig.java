package nameIndexerSearchTool;

import lombok.SneakyThrows;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import java.net.InetAddress;
import java.io.*;
import java.net.UnknownHostException;


// We need the configuration for Elasticsearch! If it works, then I'll advance even more on the project
@Configuration
@EnableElasticsearchRepositories(basePackages = "nameIndexerSearchTool.repository.elasticsearch")
public class ElasticsearchClientConfig extends AbstractElasticsearchConfiguration {


	@Override
	@Bean
	public RestHighLevelClient elasticsearchClient() {
		Boolean elasticIsAvailable=false;
		try {
			while (elasticIsAvailable){
			elasticIsAvailable = InetAddress.getByName("es01:9200").isReachable(5000);
			System.err.println("Waiting to connect to elastic server");
		}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		final ClientConfiguration clientConfiguration =
				ClientConfiguration
				.builder()
				.connectedTo("es01:9200")
				.build();

		return RestClients
				.create(clientConfiguration)
				.rest();
	}


}
