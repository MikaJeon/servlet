package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//서블릿을 컨트롤러로 사용하고, JSP를 뷰로 사용해서 MVC 패턴을 적용해보자.
//        Model은 HttpServletRequest 객체를 사용한다. request는 내부에 데이터 저장소를 가지고 있는데,
//        request.setAttribute() , request.getAttribute() 를 사용하면 데이터를 보관하고, 조회할 수 있다.
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
        public class MvcMemberFormServlet extends HttpServlet {
        @Override
        protected void service(HttpServletRequest request, HttpServletResponse
        response)
throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);//컨트롤러로 뷰로 이동하는거. 이 경로로 이동할거야!
        dispatcher.forward(request, response);//서블릿에서 jsp를 호출하는것. 리퀘스트를 주면 그 jsp를 찾아서 호출됨. 즉 저 위로 제어권을 넘김
        }//서버 내부에서 다시 호출이 발생하는게 포워드. 리다이렉트랑은 다름. url이 변경되는 것이 아니라 서버 내에서 url를 호출하는게 아니라
    //서버호출->jsp호출->맞는 jsp찾아서 응답을 띄운 것. 메소드를 썼다라고 생각하기
    //리다이렉트 : 실제 클라이언트에 응답이 감. url이 변경! 클라이언트가 리다이렉트 경로로 다시 서버로 요청하는 것. 즉 클라이언트가 인지함
    //포워드 : 서버 내부에서 일어나는 호출이라 클라이언트는 인지 할 수 없음
}