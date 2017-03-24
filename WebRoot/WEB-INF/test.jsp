<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>Testing websockets</title>
</head>
<body>
  <div>
    <input type="submit" value="打开或者刷新页面应当点击此按钮，发送进入房间请求" onclick="start()" />
    <br><br>
    <input id="text"/><br>
    <input type="submit" value="发送消息" onclick="send()" />
  </div>
  <div id="messages"></div>
  <script type="text/javascript">
  var ws;
  var WS_URL = window.location.host + "${base}" + "/websocket"
		if (location.protocol == 'http:') {
			WS_URL = "ws://"+WS_URL;
		} else { // 如果页面是https,那么必须走wss协议
			WS_URL = "wss://"+WS_URL;
		}
    var webSocket =
      new WebSocket(WS_URL);
    webSocket.onerror = function(event) {
      onError(event)
    };
 
    webSocket.onopen = function(event) {
      onOpen(event)
    };
 
    webSocket.onmessage = function(event) {
      onMessage(event)
    };
 
    function onMessage(event) {
      document.getElementById('messages').innerHTML
        += '<br />' + event.data;
    }
 
    function onOpen(event) {
      document.getElementById('messages').innerHTML
        = 'Connection established';
    }
 
    function onError(event) {
      alert(event.data);
    }
 
    function start() {
      
      var text = document.getElementById("text").value;
      webSocket.send('{"action": "in_course","course_id":1}');
      return false;
    }
    
    function send() { 
      var text = document.getElementById("text").value;
      webSocket.send('{"action":"send_msg","course_id":1,"msg":"' + text + '","video_time":3}');
      return false;
    }
  </script>
  
</body>
</html>
