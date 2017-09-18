<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	   <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
  </head> 
  
  
  <body>
    This is my JSP page. <br>
   
    <form action="api/test/insert" method="get">
			<input class="btn btn-success" type="submit" value="测试">
		</form>
   
  </body>
</html>
