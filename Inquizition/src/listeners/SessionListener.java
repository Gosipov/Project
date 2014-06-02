package listeners;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    public SessionListener() {
        
    }

    public void sessionCreated(HttpSessionEvent arg0) {
    	try {
			arg0.getSession().setAttribute("md", MessageDigest.getInstance("SHA"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

	public void sessionDestroyed(HttpSessionEvent arg0) {
        
    }
	
}
