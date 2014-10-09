<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>AJAX WebChat</title>
</head>
<body>
<center>

<h1>AJAX WebChat</h1>

<h4>Please login!</h4> 


<s:form action="ttt/Login_login">
	<s:textfield name="username" label="Username"/>
	<s:submit value="Login" />
</s:form>	

</center>
</body>
</html>