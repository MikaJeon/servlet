<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!--jsp 파일입니다 -->
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/jsp/members/save.jsp" method="post"><!--html의 form과 기본적으로는 같으나 action 부분을 주의. 전송버튼 누르면 엑션으로 이동-->
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>