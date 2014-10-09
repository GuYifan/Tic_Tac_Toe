<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
<head>
	<sx:head/>
	<title>AJAX Tic Tac Toe</title>
	<script type="text/javascript" src="ttt.js"></script>
	
</head>
<body onload="mainscreenpoll();">
<center>

<h1>AJAX TIc Tac Toe</h1>

 <s:actionmessage />

<h2>Available Users</h2>
<p>Click on a link to start a chat.</p>
<div id='userlist'>@@@@</div>
	
<s:url id="url1" action="AvailableUsers" />    	
<sx:bind id="fetchuserlist" listenTopics="getAvailableUsers" href="%{url1}" targets="userlist" />    

<h2>Invitation</h2>
<p>Click on a link to accept an invitation.</p>
<div id='invitation'>@@@@</div>

<s:url id="url2" action="Invitations" />    	
<sx:bind id="fetchinvitations" listenTopics="getInvitations" href="%{url2}" targets="invitation" />   

</center>
</body>
</html>

     

