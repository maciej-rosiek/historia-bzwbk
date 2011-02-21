package pl.rosiek.history.bean;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class HistoryReport {

	public List<HistoryEntry> read(Reader history) throws IOException {
		CSVReader csv = new CSVReader(history, ',', '"');
		List<String[]> lines = csv.readAll();
		List<HistoryEntry> entries = new ArrayList<HistoryEntry>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		for (String[] line : lines) {
			try {
				HistoryEntry entry = new HistoryEntry();
				entry.setStartDate(sdf.parse(line[0]));
				entry.setEndDate(sdf.parse(line[1]));
				entry.setTitle(line[2]);
				entry.setSender(line[3]);
				entry.setAccount(line[4]);
				entry.setAmount(line[5]);
				entry.setSaldo(line[6]);
				entry.setTransaction(line[7]);
				entries.add(entry);
			}
			catch(ParseException ex) {
				ex.printStackTrace();
			}
		}
		return entries;
	}
	
}
