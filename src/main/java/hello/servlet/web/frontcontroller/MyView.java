package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;
    public MyView(String viewPath) {
        this.viewPath = viewPath;//뷰 패스를 받으면 저장함.
    }
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);//뷰로 이동하기 위해 세팅
        dispatcher.forward(request, response);//뷰패스로 이동
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        //모델에 있는 값을 json으로 변환. jsp는 셋어트리뷰트 형식으로 데이터를 받음
        modelToRequestAtrribute(model, request);//자바8문법. key라는 변수로 for를 다 돌린다고 생각하자.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);//뷰로 이동하기 위해 세팅
        dispatcher.forward(request, response);//뷰패스로 이동
        
    }

    private void modelToRequestAtrribute(Map<String, Object> model, HttpServletRequest request) {//모델의 값을 어트리뷰트로 바꾸는 메서드
        model.forEach((key, value) -> request.setAttribute(key, value));//자바8문법. key라는 변수로 for를 다 돌린다고 생각하자.
    }
}