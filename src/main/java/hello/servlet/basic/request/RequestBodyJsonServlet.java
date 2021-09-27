package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
/**
 * http://localhost:8080/request-body-json
 *
 * JSON 형식 전송
 * content-type: application/json
 * message body: {"username": "hello", "age": 20}
 *
 */
@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-bodyjson")
public class RequestBodyJsonServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();//제이슨을 읽을 수 있는 라이브러리
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse
            response)
            throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();//바디 메세지 받기
        String messageBody = StreamUtils.copyToString(inputStream,
                StandardCharsets.UTF_8);//바디 메세지 읽어오기
        System.out.println("messageBody = " + messageBody);//제이슨 자체도 문자이기 때문에 그냥 그대로 읽어옴
        HelloData helloData = objectMapper.readValue(messageBody,
                HelloData.class);//제이슨을 파싱해서 따로 벨류만 읽어주자. 제이슨과 어디서 받았는지 타입을 넣어주면 짜잔
        System.out.println("helloData.username = " + helloData.getUsername());
        System.out.println("helloData.age = " + helloData.getAge());
        response.getWriter().write("ok");
    }
}