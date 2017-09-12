<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jsonTest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript">
	//请求json输出json
	function requestJson(){
		$.ajax({
			type: 'post',
			url:'${pageContext.request.contextPath}/requestJson.action',
			contentType:'application/json;charset=utf-8',
			//数据格式是json
			data:'{"name":"手机","price":"999"}',
			success:function(data){//返回json结果
				alert(data);
			}
			
		})
	}
	//请求key/value输出json
	function responseJson(){
		$.ajax({
			type: 'post',
			url:'${pageContext.request.contextPath}/responseJson.action',
			//请求key/value
			data:'name=手机&price=999',
			success:function(data){//返回json结果
				alert(data);
			}
			
		})
	}
	
	</script>

  </head>
  
  <body>
    <input type="button" value="请求json输出json" onclick="requestJson()">
    <input type="button"  value="请求key/value输出json" onclick="responseJson()">
  </body>
</html>
