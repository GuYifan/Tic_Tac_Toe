<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.Vector" %>
<%@ page import="csc309.ttt.User" %>

<ul>
<%   
	Vector AvailableList = (Vector) application.getAttribute("AvailableList");
	User currentUser = (User)session.getAttribute("user");
	
	currentUser.activationUpdate();
	
	if(!AvailableList.contains(currentUser)){
            AvailableList.add(currentUser);
    }
	
	currentUser.activationUpdate();
	
	synchronized(AvailableList) {
		for (int x=0; x<AvailableList.size(); x++) {
			User user = (User)AvailableList.elementAt(x);
			if (user == currentUser) {
				continue;
			} else {
				user.checkAcvitation();
				if(user.available && !user.busy){
%>   
	<s:url id="url1" action="Invite">
		<s:param name="guestuser"><%= user.userID %></s:param>
	</s:url>  
	  	
	<li><s:a href="%{url1}"><%= user.userID %></s:a> </li>

<%
				} else {
					AvailableList.remove(user);
				}
			}
		}
	}

%>     
</ul>
