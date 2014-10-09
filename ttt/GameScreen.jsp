<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="csc309.ttt.User" %>

<html>   
<head>
	<sx:head />
	<title>AJAX Tic Tac Toe</title>
	
	
	<script type="text/javascript" src="ttt.js"></script>
	
	<script type="text/javascript">
		var username="<%=((User)session.getAttribute("user")).userID %>";
	</script>
	
</head>
<body onload="gamescreenpoll();">
<center>

<h1>AJAX Tic Tac Toe</h1>

<s:actionmessage />

	<table id='gameboard'>
		<tr>
			<td id='0' onClick="makeMove('0')">*</td>
			<td id='1' onClick="makeMove('1')">*</td>
			<td id='2' onClick="makeMove('2')">*</td>
		</tr>
		<tr>
			<td id='3' onClick="makeMove('3')">*</td>
			<td id='4' onClick="makeMove('4')">*</td>
			<td id='5' onClick="makeMove('5')">*</td>
		</tr>
		<tr>
			<td id='6' onClick="makeMove('6')">*</td>
			<td id='7' onClick="makeMove('7')">*</td>
			<td id='8' onClick="makeMove('8')">*</td>
		</tr>
	</table>
	
	<div id="gameover"></div>
	<div id="turn"></div>

<s:textarea id="movebuffer" cols="80" rows="2" style="display: none;"/>

<s:url id="url2" action="GetMove" />    	
<sx:bind id="fetchmoves" listenTopics="getMoves" href="%{url2}" targets="movebuffer" afterNotifyTopics="updateGame"/>   


</center>
</body>
</html>

     

