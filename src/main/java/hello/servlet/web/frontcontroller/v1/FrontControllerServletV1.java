package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/frontcontroller/ v1/*")//뒤에 별 붙이면 이 뒤에 어떤게 와도 이걸로 연결

public class FrontControllerServletV1 extends HttpServlet {
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
        //""이 요청되면 뒤의 new가 실행된다 라는 의미로 put을 넣어줌.
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");//실행 되는지 테스트
        String requestURI = request.getRequestURI();//요청 들어온 url을 이렇게 꺼낼 수 있음
        ControllerV1 controller = controllerMap.get(requestURI);//맵에서 꺼내면 저장된 벨류인 컨트롤러가 꺼내지겠지
        //다형성에 의해서 부모 인터페이스로 받을 수 있음.
        if (controller == null) {//예외처리
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);//404 띄우기
            return;
        }
        controller.process(request, response);//각 객체에서 오버라이딩 해 만든 메서드 호출!
    }
}