package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);//핸들러가 브이쓰리 인스턴스인가? 맞으면 참 틀리면 거짓 반환
    }
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ControllerV3 controller = (ControllerV3) handler;// 핸들러를 브이쓰리로 타입변환
        Map<String, String> paramMap = createParamMap(request);//요청 파라미터 저장
        ModelView mv = controller.process(paramMap);//실제 컨트롤러에서 실제 로직 메서드 실행해서 결과 값 받아옴
        return mv;//v3는 모델뷰 반환이라 타입이 맞음. v4는 스트링 반환이라 타입 안맞음 v4는 다른 로직이 필요함
    }
    private Map<String, String> createParamMap(HttpServletRequest request) {
        //파라미터이름을 파람맵에 다 넣어주기
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));
        return paramMap;
    }
}