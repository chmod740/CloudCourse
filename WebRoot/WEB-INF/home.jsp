<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'home.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		var ws;
		var WS_URL = window.location.host + "${base}" + "/websocket"
		if (location.protocol == 'http:') {
			ws = new WebSocket("ws://"+WS_URL);
		} else { // 如果页面是https,那么必须走wss协议
			ws = new WebSocket("wss://"+WS_URL);
		}
		ws.onmessage = function(event) {
		    var re = JSON.parse(event.data);
		    if (re.action == "notify") {
		    	// 弹个浏览器通知?
		    } else if (re.action == "layer") {
		    	// 弹个层?
		    	layer.alert(re.msg);
		    }
		};
		ws.onopen = function(event) {
		    // 加入home房间
			ws.send(JSON.stringify({room:'home',"action":"join"}));
			ws.send(JSON.stringify({room:'home',"action":"join"}));
			ws.send(JSON.stringify({room:'home',"action":"join"}));
			ws.send(JSON.stringify({room:'home',"action":"join"}));
			ws.send(JSON.stringify({room:'home',"action":"join"}));
			ws.send(JSON.stringify({room:'home',"action":"join"}));
			ws.send(JSON.stringify({room:'home',"action":"join"}));
		};
	</script>
  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
