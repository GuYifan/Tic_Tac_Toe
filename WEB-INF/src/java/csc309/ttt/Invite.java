package csc309.ttt;

import java.io.*;
import javax.servlet.*; 
import javax.servlet.http.*;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;
import java.util.Vector;   

public class Invite extends ActionSupport implements ServletContextAware, SessionAware  {

	private ServletContext servletContext;
	private Map session;   
	private String guestuser;
	
	public void setGuestuser (String guest) { this.guestuser = guest; }
	public String getGuestuser() { return guestuser; }
	
	
	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
	}

	public void setSession(Map session) {
		this.session = session;
	}
	
    public String execute() {
    	Vector AvailableList = (Vector) servletContext.getAttribute("AvailableList");
        Vector InviteList = (Vector) servletContext.getAttribute("InviteList");
        
        User host = (User)session.get("user");
         
        if (AvailableList != null && getGuestuser() != null) { 
        synchronized(AvailableList) {
            for (int x=0; x < AvailableList.size(); x++) {
                User guest = (User)AvailableList.elementAt(x);
                if (guest !=null && guest.userID != null && guest.userID.compareTo(getGuestuser())==0) {

                    AvailableList.remove(guest);
                    AvailableList.remove(host);

                    Game game = new Game(host, guest);
                    
                    // indicates that the guest is invited and start timing for how long has the guest been invited in order to trigger timeout
                    guest.getInvited();
                    // to indicate that the host is engaged in a game
                    host.getBusy();

                    synchronized(InviteList) {
                    	InviteList.addElement(game);
                    }
                    
                    session.put("game",game);
                    return "success";
                }
            }
        }
        }
        addActionMessage("Sorry, " + getGuestuser() + " is not available. Please try again!");
        return "failure";
    }
    
    
}
