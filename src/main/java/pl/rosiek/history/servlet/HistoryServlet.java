package pl.rosiek.history.servlet;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreInputStream;
import pl.rosiek.history.bean.HistoryEntry;
import pl.rosiek.history.bean.HistoryReport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class HistoryServlet extends HttpServlet {

	private static final long serialVersionUID = -989625274320772398L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String blobKey = req.getParameter("blob");
    	String recentBlob = (String) req.getSession(true).getAttribute("recent-blob");
    	if (recentBlob != null && recentBlob.equals(blobKey)) {
			InputStream historyStream = new BlobstoreInputStream(new BlobKey(blobKey));
			InputStreamReader historyReader = new InputStreamReader(historyStream, "UTF-8");
			HistoryReport historyReport = new HistoryReport();
			List<HistoryEntry> history = historyReport.read(historyReader);
			historyReader.close();
			historyStream.close();
			req.setAttribute("history", history);
		    req.getRequestDispatcher("/historia/historia.jsp").forward(req, resp);
    	}
    	else {
    		resp.sendRedirect("/");
    	}
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	super.doGet(req, resp);
    }
}
