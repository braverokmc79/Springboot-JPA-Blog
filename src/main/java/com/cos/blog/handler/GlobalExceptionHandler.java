package com.cos.blog.handler;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


//@ControllerAdvice 설정을 하지 않는다.
//설정을 하지 않아도,스프링부트에서 error 로 매핑된 url 주소를 통해 에러 처리
@Controller
public class GlobalExceptionHandler implements ErrorController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 에러 페이지 정의
    private final String ERROR_404_PAGE_PATH = "error/404";
    private final String ERROR_500_PAGE_PATH = "error/500";
    private final String ERROR_ETC_PAGE_PATH = "error/error";


    //Json type 형태의 예외처리 와 CustomException 을 설정한것 이곳에서  처리된다.
    @RequestMapping(value = "/jsonError")
    @ExceptionHandler({
            CustomException.class
          //HttpRequestMethodNotSupportedException.class
    })
    @ResponseBody
    public ResponseEntity<?> JsonException(String statusCode, String msg) {
        logger.info("ajax ,json 형태의 예외처리 error ");
        if(msg!=null){
            logger.info("statusCode {}", statusCode);
            logger.info("msg {}", msg);
            return ResponseEntity.badRequest().body(statusCode + " : " +msg);
        }
        return ResponseEntity.badRequest().body("error");
    }


    @RequestMapping(value = "/error")
    @ExceptionHandler({ Exception.class })
    public String handleError(HttpServletRequest request, Model model) {

        // 에러 코드를 획득한다.
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // HttpStatus와 비교해 페이지 분기를 나누기 위한 변수
        int statusCode = 0;
        if(status != null){
            statusCode = Integer.valueOf(status.toString());
        }
        // 에러 코드에 대한 상태 정보
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);


        logger.info("에러 코드에 대한 상태 정보 :" +httpStatus);
        String contentType=request.getContentType();
        logger.info("ContentType에 대한 정보 :" +contentType);

        //1.JSON 타입으로 요청한 에러일경우 다음과 같이 처리
        if(request.getContentType()!=null && contentType.contains("application/json")){
            logger.info("JSON 방식을 통한 에러 요청 : " +request.getContentType());
            return "redirect:/jsonError?statusCode="+statusCode+"&msg="+httpStatus.getReasonPhrase();
        }



        if (status != null) {

            // 로그로 상태값을 기록 및 출력
            logger.info("httpStatus : " + statusCode);

            // 404 error
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                // 에러 페이지에 표시할 정보
                model.addAttribute("code", status.toString());
                model.addAttribute("msg", httpStatus.getReasonPhrase());
                model.addAttribute("timestamp", new Date());
                return ERROR_404_PAGE_PATH;
            }

            // 500 error
            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                // 서버에 대한 에러이기 때문에 사용자에게 정보를 제공하지 않는다.
                return ERROR_500_PAGE_PATH;
            }
        }

        // 정의한 에러 외 모든 에러는 error/error 페이지로 보낸다.
        return ERROR_ETC_PAGE_PATH;
    }


}