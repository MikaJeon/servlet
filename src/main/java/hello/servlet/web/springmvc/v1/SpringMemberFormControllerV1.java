package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller//컴포넌트로 등록해줌. 핸들러의 대상이 됨(리퀘스트매핑 등의 대상으로 만들어줌)

//이거 안쓸거면 컴포넌트랑 리퀘스트매핑 두개 애노테이션을 달아줘야 함.
public class SpringMemberFormControllerV1 {
    @RequestMapping("/springmvc/v1/members/new-form")//요청정보를 매핑함
    public ModelAndView process() {
        return new ModelAndView("new-form");//이렇게 하면 스프링 내의 뷰 리졸버가 실행되어 알맞은 jsp를 실행시켜줌
    }
}