package nameIndexerSearchTool.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import nameIndexerSearchTool.controllers.SearchController;
import nameIndexerSearchTool.models.CWJTable;
import nameIndexerSearchTool.models.Customer;
import nameIndexerSearchTool.models.JoinTableID;
import nameIndexerSearchTool.models.Watchlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerSearch {

    @Autowired
    SearchController controller;

    public List<CWJTable> checkCustomers(Collection<Customer> customersToCheck) {
        // try {
        // FileWriter myWriter = new FileWriter("src/main/resources/output/output.txt");
        HashMap<String, SearchHits<Watchlist>> customersWithCorrespondingMatches = new HashMap<>();
        List<CWJTable> clientWatchlistList= new ArrayList<>();
        for (Customer customer : customersToCheck) {
            ObjectMapper mapper = new ObjectMapper();
            List<String> namesToSearch = new ArrayList<>();
            String wholeName = customer.getWhole_name();
            if (!(wholeName.isEmpty() || wholeName.trim().isEmpty() || wholeName.trim().equals("null") || wholeName.trim().contains("\""))) {
                // theFullName += wholeName.trim();
                namesToSearch.add(wholeName.trim());
            }
            String businessName = customer.getBusiness_name();
            if (!(businessName.isEmpty() || businessName.trim().isEmpty() || businessName.trim().equals("null") || businessName.trim().contains("\""))) {
                // theFullName += businessName.trim();
                namesToSearch.add(wholeName.trim());

            }
            String firstName = customer.getFirst_name();
            if (!(firstName.isEmpty() || firstName.trim().isEmpty() || firstName.trim().equals("null") || firstName.trim().contains("\""))) {
                // theFullName += firstName.trim();
                namesToSearch.add(firstName.trim());

            }
            String lastName = customer.getLast_name();
            if (!(lastName.isEmpty() || lastName.trim().isEmpty() || lastName.trim().equals("null") || lastName.trim().contains("\""))) {
                // theFullName += lastName.trim();
                namesToSearch.add(lastName.trim());

            }
            String maidenName = customer.getMaiden_name();
            if (!(maidenName.isEmpty() || maidenName.trim().isEmpty() || maidenName.trim().equals("null") || maidenName.trim().contains("\""))) {
                // theFullName += maidenName.trim();
                namesToSearch.add(maidenName.trim());

            }
            String managerName = customer.getManager_name();
            if (!(managerName.isEmpty() || managerName.trim().isEmpty() || managerName.trim().equals("null") || managerName.trim().contains("\""))) {
                // theFullName += managerName.trim();
                namesToSearch.add(managerName.trim());

            }
            String gazetteRef = customer.getGazette_ref();
            if (!(gazetteRef.isEmpty() || gazetteRef.trim().isEmpty() || gazetteRef.trim().equals("null") || gazetteRef.trim().contains("\""))) {
                // theFullName += gazetteRef.trim();
                namesToSearch.add(gazetteRef.trim());

            }

            String theFullName = namesToSearch.stream().collect(Collectors.joining(" "));

            SearchHits<Watchlist> results = controller.checkName(theFullName);


            results.forEach(
                    element -> {

                        CWJTable clientWatchlistHit = new CWJTable(new JoinTableID(customer.getId(), element.getId())
                                , element.getScore(), element.getContent().getPrimary_name(),
                                namesToSearch.stream().collect(Collectors.joining(" ")));
                        System.out.println(clientWatchlistHit);
                        clientWatchlistList.add(clientWatchlistHit);
                    }
            );
        }
        return clientWatchlistList;

    }
}
