package listeners;

import helpers.FriendManager;
import helpers.Sign;

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
		arg0.getSession().setAttribute("sign", new Sign());
    }

	public void sessionDestroyed(HttpSessionEvent arg0) {
        
    }
	
}
