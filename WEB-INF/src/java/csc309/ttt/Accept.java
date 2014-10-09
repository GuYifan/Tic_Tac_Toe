package csc309.ttt;

import java.io.*;
import javax.servlet.*; 
import javax.servlet.http.*;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;
import java.util.Vector;   
 
public class Accept extends ActionSupport implements ServletContextAware, SessionAware  {

	private ServletContext servletContext;
	private Map session;   
	private String hostuser;
	
	public void setHostuser (String host) { this.hostuser = host; }
	public String getHostuser() { return hostuser; }
	
	
	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
	}

	public void setSession(Map session) {
		this.session = session;
	}
	
	// Accept an invitation
    public String execute() {

    	
        Vector InviteList = (Vector) servletContext.getAttribute("InviteList");
        Vector GameList = (Vector) servletContext.getAttribute("GameList");
        
        User myself = (User)session.get("user");
            
        synchronized(InviteList) {
            for (int x=0; x < InviteList.size(); x++) {
                
                Game game = (Game)InviteList.elementAt(x);
                if (game !=null && game.guest == myself &&
                    	game.host.userID.compareTo(getHostuser()) == 0	) {
                        InviteList.remove(game);
                        
                        // It means that this user is in a game 
                        game.guest.getBusy();
                        
                        synchronized(GameList) {
                        	GameList.add(game);
                        }
                        
                        session.put("game",game);

                        return "success";
                    }
            }
        }
        
        addActionMessage("Sorry, " + getHostuser() + " is not available. Please try again!");
        return "failure";
    }
    
    
}

        
