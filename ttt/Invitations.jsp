<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.Vector" %>
<%@ page import="csc309.ttt.User" %>
<%@ page import="csc309.ttt.Game" %>

<ul>
<%
	Vector InviteList = (Vector) application.getAttribute("InviteList");
	User currentUser = (User)session.getAttribute("user");
	
	currentUser.activationUpdate();
	
	synchronized(InviteList) {
		for (int x=0; x<InviteList.size(); x++) {
			Game game = (Game)InviteList.elementAt(x);
            if (currentUser == game.guest) {
	            game.host.checkAcvitation();
	            if(game.host.available){
%>
	<s:url id="url1" action="Accept">
		<s:param name="hostuser"><%=game.host.userID %></s:param>
	</s:url>  
	  	
	<li><s:a href="%{url1}"><%= game.host.userID %></s:a> </li>

<%
				} else {
					InviteList.remove(game);
				}
            }
		}	
	}

%>     
</ul>


 