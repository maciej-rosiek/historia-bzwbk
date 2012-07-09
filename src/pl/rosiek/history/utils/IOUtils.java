package pl.rosiek.history.utils;

import java.io.IOException;
import java.io.Reader;

public class IOUtils {

	public static String convertToString(Reader in) throws IOException {
		final char[] buffer = new char[0x10000];
		StringBuilder out = new StringBuilder();
		try {
			int read;
			do {
				read = in.read(buffer, 0, buffer.length);
				if (read > 0) {
					out.append(buffer, 0, read);
				}
			} while (read >= 0);
		} finally {
			in.close();
		}
		return out.toString();
	}
}
