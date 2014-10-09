package csc309.ttt;

import java.util.Vector;
import javax.servlet.http.*;
import javax.servlet.ServletContext;

public class Initialization extends HttpServlet {
	public void init() {
		try {
			ServletContext context = this.getServletContext();
	        context.setAttribute("AvailableList", new Vector());
	        context.setAttribute("InviteList", new Vector());
	        context.setAttribute("GameList", new Vector());
		}
		catch (Exception ex) {
		    getServletContext().log(ex.getMessage());
		}
	}
}


