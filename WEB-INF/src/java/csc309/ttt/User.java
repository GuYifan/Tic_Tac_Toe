package csc309.ttt;

import java.util.*;

public class User {
    public String userID;
    public boolean available = true;
    public boolean busy = false;
    
    // variable that records the last time point the user responds
    long lastCheckedTime;
    // variable that indicates the time point that the user gets invited
    long invitationWaitTime;
    
    /** Creates a new instance of User */
    public User(String userID) {
        this.userID = userID;
        lastCheckedTime = System.currentTimeMillis();
    }
    
    // update the last time point the user checked in
    public void activationUpdate(){
    	this.lastCheckedTime = System.currentTimeMillis();
    }
    
    // check if the user still active by checking how long has not the user responded 
    public void checkAcvitation(){
    	
    	long interval = System.currentTimeMillis() - this.lastCheckedTime;
    	if(interval >= 120000){
    		available = false;
    	} else {
    		available = true;
    	}
    }
    
    // to mark the time point where the user is get invited
    public void getInvited(){
    	this.invitationWaitTime = System.currentTimeMillis();
    }
    
    // check if the user is interested in the invitation if the usre is invited to a game by checking how long has the user not responded to 
    // the invitation
    public boolean checkAcception(){
    	long interval = System.currentTimeMillis() - this.invitationWaitTime;
    	if(interval >= 10000){
    		this.invitationWaitTime = System.currentTimeMillis();
    		return true;
    	}
    	return false;
    }
    
    // to indicate that the user is engaged in a game
    public void getBusy(){
    	this.busy = true;
    }
    
    // return true if the user is in a game
    public boolean isBusy(){
    	return busy;
    }
    
    // return true if the user is in a game
    public void notBusy(){
    	this.busy = false;
    }
    
}   
