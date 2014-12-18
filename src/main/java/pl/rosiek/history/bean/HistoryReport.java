package pl.rosiek.history.bean;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import pl.rosiek.history.utils.IOUtils;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoryReport {

    private static final Logger log = Logger.getLogger(HistoryReport.class.getName());
    
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	public List<HistoryEntry> read(Reader history) throws IOException {
		Reader cleanHistory = cleanHistory(history);
		CSVFormat format = CSVFormat.newFormat(',').withQuote('"').withCommentMarker('-');
		CSVParser parser = new CSVParser(cleanHistory, format);
		List<HistoryEntry> entries = new ArrayList<HistoryEntry>();
		List<CSVRecord> lines = parser.getRecords();
		if (lines != null) {
			for (CSVRecord line : lines) {
				HistoryEntry entry = createEntry(line);
				if (entry != null)
					entries.add(entry);
			}
		}
		return entries;
	}

	private HistoryEntry createEntry(CSVRecord line) {
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

	private String getValue(CSVRecord line, int index) {
		return line.size() >= (index + 1) ? line.get(index) : "";
	}

	private String joinLine(CSVRecord line) {
		String result = "";
		for (String l : line)
			result += l + " | ";
		return result;
	}

	private Reader cleanHistory(Reader in) throws IOException {
		String history = IOUtils.convertToString(in);
		history = history.replaceAll("\"\"", "\"");
		return new StringReader(history);
	}

}
