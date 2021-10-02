<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] web-INF는 이전에는 그냥 경로로 jsp파일을 불러올 수 있었음
 그런데 이 파일은 컨트롤러를 거쳐서 불렀으면 좋겠고 절대 경로로 찍히지 않았으면 좋겠으면 이 안에 넣으면 됨. 룰임! 그냥 호출이 안되게
 막아줍니다-->
<form action="save" method="post"><!--액션이 /save가 아니라 그냥 save임. 상대경로 사용 시 현제 url의 계층+save로 호출됨-->
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
