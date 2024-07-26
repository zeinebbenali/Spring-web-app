import org.apache.commons.csv.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    public List<Person> importCsv(MultipartFile file) throws IOException {
        List<Person> people = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            for (CSVRecord csvRecord : csvParser) {
                Person person = new Person();
                person.setFirstName(csvRecord.get("First Name"));
                person.setLastName(csvRecord.get("Last Name"));
                people.add(person);
            }
        }
        return people;
    }

    public ByteArrayInputStream exportCsv(List<Person> people) throws IOException {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {

            csvPrinter.printRecord("First Name", "Last Name");
            for (Person person : people) {
                csvPrinter.printRecord(person.getFirstName(), person.getLastName());
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
