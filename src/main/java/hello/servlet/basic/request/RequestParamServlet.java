package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 * <p>
 * 2. 동일한 파라미터 전송 가능
 * http://localhost:8080/request-param?username=hello&username=kim&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse
            resp) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");
 /*
 Enumeration<String> parameterNames = request.getParameterNames();
 while (parameterNames.hasMoreElements()) {
 String paramName = parameterNames.nextElement();
 System.out.println(paramName + "=" +
request.getParameter(paramName));
 }
 */
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName +
                        "=" + request.getParameter(paramName)));//겟파라미터네임하면 파라미터 다 받아올 수 있음.
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();
        System.out.println("[단일 파라미터 조회]");
        String username = request.getParameter("username");
        System.out.println("request.getParameter(username) = " + username);
        String age = request.getParameter("age");
        System.out.println("request.getParameter(age) = " + age);
        System.out.println();
        System.out.println("[이름이 같은 복수 파라미터 조회]");//이렇게 안하면 먼저 잡힌게 출력됨
        System.out.println("request.getParameterValues(username)");
        String[] usernames = request.getParameterValues("username");//배열로 파라미터를 받을 수 있음.
        for (String name : usernames) {
            System.out.println("username=" + name);
        }
        resp.getWriter().write("ok");
    }
}