package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        return new MyView("/WEB-INF/views/new-form.jsp");
        //마이뷰 형식으로 패스를 넣어 리턴시킴. 변수=뉴 리턴변수 이걸 줄인게 요 방식
        //원래는 경로 넣고 디스패쳐하고 포워드까지 해줬어야 했음. 그래야 이동이 됐는데 지금은 url넣어서 리턴하면 끝
    }
}