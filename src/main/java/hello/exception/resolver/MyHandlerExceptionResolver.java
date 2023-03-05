package hello.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("call resolver",ex);
        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IlligalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        // 만약 if에서 잡히지 않았을 경우에는 그냥 null로 보내서 기존 exception 터진 것으로 보낸다.
        return null;
    }
}
