package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet(name = "frontControllerServletV5", urlPatterns = "/frontcontroller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    //컨트롤러를 핸들러로 표현
    //핸들러를 매핑하기 위한 해쉬맵과 리스트들
    private final Map<String, Object> handlerMappingMap = new HashMap<>();//모든 핸들러 지원을 위해 맵 구조는 오브젝트!
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());//해시맵이 오브젝트이기때문에 잘 담김
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        //V4추가
        handlerMappingMap.put("/front-controller/v4/v3/members/new-form", new MemberFormControllerV3());//해시맵이 오브젝트이기때문에 잘 담김
        handlerMappingMap.put("/front-controller/v4/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v4/v3/members", new MemberListControllerV3());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());//리스트에 어댑터 담아줌
        handlerAdapters.add(new ControllerV4HandlerAdapter());//리스트에 어댑터 담아줌
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request);

        if (handler == null) {//에러처리
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        MyHandlerAdapter adapter = getHandlerAdapter(handler);//핸들러 어댑터를 찾아야 함
        ModelView mv = adapter.handle(request, response, handler);//어댑터가 실제 컨트롤러에 접근하는 메서드를 실행
        MyView view = viewResolver(mv.getViewName());//URL받아서 FULL로 만듦
        view.render(mv.getModel(), request, response);//랜더! 실행해줌
    }

    private Object getHandler(HttpServletRequest request) {//매핑정보를 가지고 매핑할 객체를 찾음
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {//FOR문을 돌려서 맞는 핸들러어댑터를 찾음
        for (MyHandlerAdapter adapter : handlerAdapters) {//어댑터 담아둔 거에서 FOR문
            if (adapter.supports(handler)) {//어댑터가 핸들러를 지원하는가?
                return adapter;//지원하면 반환
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);//없을때 예외처리
    }
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");//FULL URL 만들기
    }
}