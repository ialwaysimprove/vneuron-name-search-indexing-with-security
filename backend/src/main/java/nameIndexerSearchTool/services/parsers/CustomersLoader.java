package nameIndexerSearchTool.services.parsers;

import lombok.extern.slf4j.Slf4j;
import nameIndexerSearchTool.models.Customer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileReader;
import java.io.InputStream;
import java.util.*;

@Slf4j
public class CustomersLoader {

    private static final String TAB_DELIMITER = "\t";
    public static Collection<Customer> prepareDataset() {
        Resource resource = new ClassPathResource("exportCustomers.csv");
        List<Customer> customerList = new ArrayList<Customer>();

        try (
                InputStream input = resource.getInputStream();
                Scanner scanner = new Scanner(resource.getInputStream());) {
            int lineNo = 0;
            while (scanner.hasNextLine()) {
                ++lineNo;
                String line = scanner.nextLine();
                if(lineNo == 1) continue;
                Optional<Customer> watchlist_element =
                        csvRowToWatchlistMapper(line);
                if(watchlist_element.isPresent())
                    customerList.add(watchlist_element.get());
            }
        } catch (Exception e) {
            log.error("File read error {}",e);;
        }
        System.out.println(customerList);
        return customerList;
    }
    private static Optional<Customer> csvRowToWatchlistMapper(final String line) {
        List<String> record_values = new ArrayList<String>();

        String fileName = "src/main/resources/csv/exportCustomers.csv";
        try (
                Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(TAB_DELIMITER);
            while (rowScanner.hasNext()) {
                record_values.add(rowScanner.next());
            }
            Long entityid = record_values.get(0).trim().isEmpty() ? 999999 : Long.parseLong(record_values.get(0).trim());
            String businessname = record_values.get(9).trim().isEmpty()?"":record_values.get(9).trim();
            String firstname = record_values.get(37).trim().isEmpty()?"":record_values.get(37).trim();
            String lastname = record_values.get(43).trim().isEmpty()?"":record_values.get(43).trim();
            String maidenname = record_values.get(45).trim().isEmpty()?"":record_values.get(45).trim();
            String managername = record_values.get(46).trim().isEmpty()?"":record_values.get(46).trim();
            String wholename = record_values.get(69).trim().isEmpty()?"":record_values.get(69).trim();
            String gazetteRef = record_values.get(40).trim().isEmpty()?"":record_values.get(40).trim();
            //System.out.println(wholename);
            return Optional.of(
                    Customer.builder()
                            .id(entityid)
                            .business_name(businessname)
                            .first_name(firstname)
                            .last_name(lastname)
                            .maiden_name(maidenname)
                            .manager_name(managername)
                            .whole_name(wholename)
                            .gazette_ref(gazetteRef)
                            .build());

        }
        catch (Exception exception) {
            System.out.println("An Exception in CSV Parser");
        }
        return null;
    }
}
