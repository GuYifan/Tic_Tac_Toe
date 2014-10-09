package csc309.ttt;

import java.util.Vector;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import sun.org.mozilla.javascript.internal.Context;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

import javax.servlet.ServletContext;


// to handle the case where the user make moves and get update from the competitor
public class Move extends ActionSupport implements ServletContextAware, SessionAware {
	private Map session;   
	private String move;
    public String[] gameboard;
    private String winner;
    private String draw;
    private String symbol;
    // to indicates the user whose turn it is
    private String turn;
	private ServletContext servletContext;
	// to indicate if the competitor is offline; if the competitor is offline, it becomes 1
	private String oppoOffline = null;
	// to indicate if the guest is not interested; if so it becomes 1
	private String rejection;
	
	
	// setters and getters
	public void setMove (String m) { this.move = m; }
	public String getMove() { return move; }
	
	public void setDraw (String draw) { this.draw = draw; }
	public String getDraw() { return draw; }
	
	public void setOppoOffline (String oppoOffline) { this.oppoOffline = oppoOffline; }
	public String getOppoOffline() { return oppoOffline; }
	
	private void setGameboard(String[] gb) { this.gameboard = gb; } 
	public String[] getGameboard () { return gameboard; }
	
	public void setWinner (String m) { this.winner = winner; }
	public String getWinner() { return winner; }
	
	public void setSymbol (String symbol) { this.symbol = symbol; }
	public String getSymbol() { return symbol; }
	
	public void setTurn (String turn) { this.turn = turn; }
	public String getTurn() { return turn; }
	
	public void setRejection (String turn) { this.rejection = rejection; }
	public String getRejection() { return rejection; }
	
	public void setSession(Map session) {
		this.session = session;
	}
	
	public void setGB(String[] gameboard) {
		this.gameboard = gameboard;
	}
	
	// send the move a user made
    public String send() {
        User currentUser = (User)session.get( "user" );
        Game game = (Game)session.get( "game" );
        
        if(game.over){
        	return "success";
        }
        
        if (game != null && getMove() != null) {
            synchronized(game) {
	            String[] tmp = game.gameboard;
	            int position = Integer.parseInt(move);
	            // to know if the move is made by the host or guest
	            if (game.host == currentUser) {
	            	// to check if the host has the turn to make a move
	            	if(game.guestTurn){
	            		return "success"; 
	            	}
	            	// to check if the move is valid
	            	if(tmp[position] == "*"){
	            		tmp[position] = "X";
		            	game.setBoard(tmp);
		            	game.guestTurn();
		            	game.makeStep();
	            	}
	            }	
	            else {
	            	// to check if the guest has the turn to make a move
	            	if(game.guestTurn){
	            		// to check if the move is valid
	            		if(tmp[position] == "*"){
			            	tmp[position] = "O";
			            	game.setBoard(tmp);
			            	game.hostTurn();
			            	game.makeStep();
	            		}
	            	}
	            }
            }
        }
        return "success";
    }
    
    // to get the move from server to update the game board
    public String get() {
        Game game = (Game)session.get( "game" );
        User user = (User)session.get( "user" );
        User oppo;
        
        // to check in; update the activation status
        user.activationUpdate();
        
        // to know whether the user is the guest or host
        if(user.userID == game.host.userID){
        	oppo = game.guest;
        } else {
        	oppo = game.host;
        }
        
        if (game != null) {
            synchronized(game) {
            	// to check if the game is over
            	if(game.isOver()){
            		// to check if the game is a draw
            		if(game.draw){
            			// set teh flag indicating the game is a draw
                		draw = "1";
                	}else {
                		winner = game.winner.userID;
                    	symbol = game.winSymbol;
                	}
            	}
            	setGameboard(game.gameboard);
            }
        }
        
        // to updates the turn information if the game is not over
        if(!game.over){
        	if(game.guestTurn){
            	if(game.guest.userID == user.userID){
            		setTurn("Your Turn");
            	} else {
            		setTurn("Please wait for " + game.guest.userID + "'s turn.");
            	}
            } else {
            	if(game.host.userID == user.userID){
            		setTurn("Your Turn");
            	} else {
            		setTurn("Please wait for " + game.host.userID + "'s turn.");
            	}
            }
        }
        
        // check if the competitor is still active, if so put the user back to availabel list
        oppo.checkAcvitation();
        if(!oppo.available){
        	Vector AvailableList = (Vector) servletContext.getAttribute("AvailableList");
        	user.notBusy();
        	if(!AvailableList.contains(user)){
        		AvailableList.add(user);
        	}
        	session.remove(game);
        	oppoOffline = "1";
        	setTurn("Your competitor is offline.");
        }
        
        // the following is to check if the competitor is not interested in the invitation. if so close the game and put both 
        // host and guset back to available list and remove the game from game list
        if(!oppo.isBusy() && !game.isOver()){
        	if(oppo.checkAcception()){
        		user.notBusy();
            	Vector AvailableList = (Vector) servletContext.getAttribute("AvailableList");
            	if(!AvailableList.contains(user)){
            		AvailableList.add(user);
            	}
            	if(!AvailableList.contains(game.guest)){
            		AvailableList.add(game.guest);
            	}
            	
            	Vector InviteList = (Vector) servletContext.getAttribute("InviteList");
            	InviteList.remove(game);
            	
            	Vector GameList = (Vector) servletContext.getAttribute("GameList");
            	GameList.remove(game);
            	
            	session.remove(game);
            	rejection = "1";
            	setTurn("Your invitation is rejected...");
            }
        }
        
        
        return "success";
    }
    
	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
	}
}