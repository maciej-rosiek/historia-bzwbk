package pl.rosiek.history.bean;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

public class HistoryReportTest {

	private static final String TEXT_EXAMPLE = 
			"2012-07-09,04-07-2011,'29 1090 2330 0000 0001 0221 4019,ROSIEK S. AUTO SERWIS  UL. NIEPODLEG�O�CI 47 57-400 NOWA RUDA DOLNO�L�SKIE,PLN,\"\"11542,02\"\",\"\"19460,70\"\",1489,\n" +
			"06-07-2012,06-07-2012,ZAP�.F-RY NR 710999079/16R/2012IDENT. 710999079,\"PGE OBR�T S.A., KILI�SKIEGO 76, 90-119 ��D�\",81 1240 6960 1036 0000 0999 0790,\"\"-909,45\"\",\"\"19460,70\"\",1,\n";

	@Test
	public void shouldReturnEmptyListForEmptyCSV() throws IOException {
		HistoryReport report = new HistoryReport();
		Reader history = new StringReader("");
		List<HistoryEntry> entries = report.read(history);
		Assert.assertNotNull(entries);
		Assert.assertEquals(0, entries.size());
	}

	@Test
	public void shouldReturnEntriesListForExampleCSV() throws IOException {
		HistoryReport report = new HistoryReport();
		Reader history = new StringReader(TEXT_EXAMPLE);
		List<HistoryEntry> entries = report.read(history);
		Assert.assertNotNull(entries);
		Assert.assertEquals(2, entries.size());
	}
	
}
