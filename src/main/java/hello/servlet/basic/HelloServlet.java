package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse
            response)//이 서블릿이 호출되면 이 서비스가 실행 됨. 요청이 들어가면 was가 리퀘스트를 만들고 응답을 위한 리스폰스를 만들어서 여기에 줌
            throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);
        String username = request.getParameter("username");//파라미터를 받아옴
        System.out.println("username = " + username);
        response.setContentType("text/plain");//응답으로 보낼 메세지는 여기에 담아줌. 이거랑
        response.setCharacterEncoding("utf-8");//이거는 헤더 정보에 들어감.
        response.getWriter().write("hello " + username);//라이트로 적으면 바디에 들어감
    }
}