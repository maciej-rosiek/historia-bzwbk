package pl.rosiek.history.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 970805354186205611L;
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String uploadUrl = blobstoreService.createUploadUrl("/upload");
		req.setAttribute("url", uploadUrl);
        req.getRequestDispatcher("/historia/upload.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
        BlobKey blobKey = blobs.get("csv");
        if (blobKey == null) {
            res.sendRedirect("/upload");
        } else {
        	req.getSession(true).setAttribute("recent-blob", blobKey.getKeyString());
            res.sendRedirect("/history?blob=" + blobKey.getKeyString());
        }
    }

}
