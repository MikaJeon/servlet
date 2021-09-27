package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * http://localhost:8080/response-header
 *
 */
@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse
            response)
            throws ServletException, IOException {
        //[status-line]http 응답의 첫 줄을 스테이튜스 라인이라고 함.
        response.setStatus(HttpServletResponse.SC_OK); //성공하면 기본이 200 http 응답 코드를 세팅해줌
        //[response-headers]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");//한글을 넣어주기 위해 캐릭터셋 설정
        response.setHeader("Cache-Control", "no-cache, no-store, mustrevalidate");//캐쉬를 완전히 무효화 하겠다
        response.setHeader("Pragma", "no-cache");//캐쉬 과거버전도 없앨 것이다.
        response.setHeader("my-header","hello");//임의의 헤더를 만듦.
        //[Header 편의 메서드]
        content(response);
        cookie(response);
        redirect(response);
        //[message body]
        PrintWriter writer = response.getWriter();//리스판스에 쓰자
        writer.println("ok");
    }


    private void content(HttpServletResponse response) {//컨텐트 메서드를 만들고 위 셋 헤더를 여기서 설정해줘도 됨
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }
    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }
    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}