package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);//핸들러가 v4를 지원하는가
    }
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ControllerV4 controller = (ControllerV4) handler;//형변환~
        Map<String, String> paramMap = createParamMap(request);//파라미터 받기
        Map<String, Object> model = new HashMap<>();//컨트롤러v4는 모델을 받으니까 빈모델 만들기
        String viewName = controller.process(paramMap, model);//파람이랑 빈 모델을 컨트롤러v4로 보내서 로직 태우기

        ModelView mv = new ModelView(viewName);//결과 값 모델에 담기 즉 모델 뷰에 뷰 세팅
        mv.setModel(model);//모델 뷰에 모델 세팅해주기

        return mv;
    }
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));
        return paramMap;
    }
}