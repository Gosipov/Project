package listeners;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mysql.jdbc.Statement;

import db.DBConnection;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

	DBConnection db;
    
    public ContextListener() {
    	db = new DBConnection();
    }

    public void contextInitialized(ServletContextEvent arg0) {
        arg0.getServletContext().setAttribute("database", db);
    }

    public void contextDestroyed(ServletContextEvent arg0) {
    	db.close();
    }
	
}
