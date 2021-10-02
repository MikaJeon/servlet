<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %><!--임포트 하듯 페이지도 임포트 해줘야 함.-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // request, response 사용 가능 jsp도 어차피 서블릿으로 변환되기 때문. 그래서 서블릿은 그냥 사용 가능
    MemberRepository memberRepository = MemberRepository.getInstance();
    System.out.println("save.jsp");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    Member member = new Member(username, age);
    System.out.println("member = " + member);
    memberRepository.save(member);
%><!--자바 코드를 넣는 로직!-->
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>