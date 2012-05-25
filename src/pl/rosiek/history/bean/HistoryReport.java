package pl.rosiek.history.bean;

import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVStrategy;

public class HistoryReport {

    private static final Logger log = Logger.getLogger(HistoryReport.class.getName());
    
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	public List<HistoryEntry> read(Reader history) throws IOException {
		CSVStrategy strategy = new CSVStrategy(',', '"', '-');
		CSVParser parser = new CSVParser(history, strategy);
		String[][] lines = parser.getAllValues();
		List<HistoryEntry> entries = new ArrayList<HistoryEntry>();
		for (String[] line : lines) {
			HistoryEntry entry = createEntry(line);
			if (entry != null)
				entries.add(entry);
		}
		return entries;
	}

	private HistoryEntry createEntry(String[] line) {
		HistoryEntry entry = null;
		try {
			entry = new HistoryEntry();
			entry.setStartDate(sdf.parse(getValue(line, 0)));
			entry.setEndDate(sdf.parse(getValue(line, 1)));
			entry.setTitle(getValue(line, 2));
			entry.setSender(getValue(line, 3));
			entry.setAccount(getValue(line, 4));
			entry.setAmount(getValue(line, 5));
			entry.setSaldo(getValue(line, 6));
			entry.setTransaction(getValue(line, 7));
		}
		catch(Exception ex) {
			String joinedLine = joinLine(line);
			log.log(Level.WARNING, "Parsing line: '" + joinedLine + "' caused an error.", ex);
		}
		return entry;
	}

	private String getValue(String[] line, int index) {
		return line.length >= (index + 1) ? line[index] : "";
	}

	private String joinLine(String[] line) {
		String result = "";
		for (String l : line)
			result += l + " | ";
		return result;
	}
	
}
