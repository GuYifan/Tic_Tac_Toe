package csc309.ttt;

import java.util.Arrays;
import java.util.Vector;


// The game object, which represents a Tic Tac Toe game
public class Game {
	
    public User host;
    public User guest;
    public String[] gameboard = {"*","*","*","*","*","*","*","*","*",};
    // flag that indicates if the game is over
    public boolean over;
    // flag that indicates if the game is a draw
    public boolean draw;
    // boolean indicating that if the current turn is for guest
    public boolean guestTurn;
    public User winner;
    // counter which indicates the total number of moves of both users
    public int steps;
    // indicates the symbol used by the winner. "X" if the winner is the game host; "O" otherwise.
    public String winSymbol;
    
    public Game(User u1, User u2) {
        host = u1;
        guest = u2;
        over = false;
        draw = false;
    	guestTurn = false;
        steps = 0;
    }
    
    public void setBoard(String[] gb){
    	this.gameboard = gb;
    }
    
    // check if the current turn is for guest
    public void guestTurn(){
    	guestTurn = true;
    }
    
    // check if the current turn is for host
    public void hostTurn(){
    	guestTurn = false;
    }
    
    // increment the counter
    public void makeStep(){
    	steps+=1;
    }
    
    // return true if the game is over
    public boolean isOver(){
    	if(over){
    		return over;
    	}
    	checkOver();
    	return over;
    }
    
    
    // check is the game is over
    private boolean checkOver(){
    	if(over) {
    		return over;
    	} else {
    		if((gameboard[0] != "*") && (gameboard[0] == gameboard[1]) && (gameboard[1] == gameboard[2])){
        		checkHelper(gameboard[0]);
        		return over;
        	}
        	
        	if((gameboard[3] != "*") && (gameboard[3] == gameboard[4]) && (gameboard[4] == gameboard[5])){
        		checkHelper(gameboard[3]);
        		return over;
        	}
        	
        	if((gameboard[6] != "*") && (gameboard[6] == gameboard[7]) && (gameboard[7] == gameboard[8])){
        		checkHelper(gameboard[6]);
        		return over;
        	}
        	
        	if((gameboard[3] != "*") && (gameboard[0] == gameboard[3]) && (gameboard[3] == gameboard[6])){
        		checkHelper(gameboard[0]);
        		return over;
        	}
        	
        	if((gameboard[4] != "*") && (gameboard[1] == gameboard[4]) && (gameboard[4] == gameboard[7])){
        		checkHelper(gameboard[1]);
        	}
        	
        	if((gameboard[5] != "*") && (gameboard[2] == gameboard[5]) && (gameboard[5] == gameboard[8])){
        		checkHelper(gameboard[2]);
        		return over;
        	}
        	
        	if((gameboard[4] != "*") && (gameboard[0] == gameboard[4]) && (gameboard[4] == gameboard[8])){
        		checkHelper(gameboard[0]);
        		return over;
        	}
        	
        	if((gameboard[4] != "*") && (gameboard[2] == gameboard[4]) && (gameboard[4] == gameboard[6])){
        		checkHelper(gameboard[2]);
        		return over;
        	}
        	this.checkDraw();
        	return over;
    	}
    }
    
    // set the winner and winning symbol
    private void checkHelper(String symbol) {
		over = true;
		if(symbol == "X"){
			winner = host;
			winSymbol = "X";
		} else {
			winner = guest;
			winSymbol = "O";
		}
    }
    
    
    // check if the current game is a draw
    private void checkDraw(){
    	for(int i=0; i<9; i++){
    		if(gameboard[i] == "*"){
    			return;
    		}
    	}
    	this.over = true;
    	this.draw = true;
    }
}