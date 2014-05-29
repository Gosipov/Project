package listeners;

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
        
    }

	public void sessionDestroyed(HttpSessionEvent arg0) {
        
    }
	
}
