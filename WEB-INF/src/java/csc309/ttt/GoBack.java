package csc309.ttt;

import java.io.*;
import javax.servlet.*; 
import javax.servlet.http.*;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;
import java.util.Vector;


// class to handle the case where the game is over and the user wants to exit
public class GoBack extends ActionSupport implements ServletContextAware, SessionAware  {
	
	private ServletContext servletContext;
	private Map session;
	
	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
	}

	public void setSession(Map session) {
		this.session = session;
	}
	
	public void back(){
		
		// put the user back to available list
		Vector AvailableList = (Vector) servletContext.getAttribute("AvailableList");
		User myself = (User)session.get("user");
		myself.notBusy();
		if(!AvailableList.contains(myself)){
    		AvailableList.add(myself);
    	}
		
		
		// remove the game from game list
		Vector GameList = (Vector) servletContext.getAttribute("GameList");
		Game game = (Game)session.get( "game" );
		if(GameList.contains(game)){
			GameList.remove(game);
    	}
	}

}
