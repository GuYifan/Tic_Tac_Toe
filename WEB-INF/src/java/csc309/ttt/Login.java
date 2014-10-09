package csc309.ttt;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.util.ServletContextAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.ServletContext;

import java.util.Map;
import java.util.Vector;   



public class Login extends ActionSupport implements ServletContextAware, SessionAware {
   
	private ServletContext servletContext;
	private Map session;   
	  
    
	private String username;
	
    public String getUsername() { return username;}
    public void setUsername(String u) { this.username = u; }
	
	public String show() {
		return "input";
	}
	
	public String login() {
        Vector AvailableList = (Vector) servletContext.getAttribute("AvailableList");
    
        User user = new User(getUsername());
        
        synchronized(AvailableList) {
          AvailableList.addElement(user);
        }
           
        session.put("user", user);
		
        return "success";
	}
	

	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
	}

	public void setSession(Map session) {
		this.session = session;
	}
	
	/* Validateable Implementation */
	public void validate() {
		/* Check that fields are not empty */
		if (getUsername() == null || getUsername().length() == 0) {
			addFieldError("username", getText("username.required"));
		}
	}  
}
