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
    <input type="submit" value="Start" onclick="start()" />
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
      webSocket.send('hello');
      return false;
    }
  </script>
</body>
</html>
