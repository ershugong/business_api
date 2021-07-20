package cn.ag.channel.exception;

import cn.ag.channel.jsonResult.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/***
 * 全局异常处理类
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler()
    @ResponseBody
    public JsonResult defultMyExcepitonHandler(HttpServletRequest request, Exception e) {
        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        e.printStackTrace();
        JsonResult jsonResult = new JsonResult();
        //自定义的异常抛出
        if(e instanceof MyException) {
            MyException myException = (MyException) e;
            jsonResult.setCode(myException.getCode());
            jsonResult.setData(new ArrayList<>());
            jsonResult.setMessage(myException.getMessage());
        }else{
            jsonResult.setCode(-1);
            jsonResult.setData(new ArrayList<>());
            jsonResult.setMessage("系统异常,请稍后再试");
            //异常类全路径
            log.error("path: " + stackTraceElement.getClassName());
            //异常行数
            log.error("line: " + stackTraceElement.getLineNumber());
            //异常信息
            log.error("error: " + e.toString());
            //异常请求地址
            log.error("url: " + request.getRequestURI());
            //异常请求参数
            StringBuffer sb = new StringBuffer();
            if(request.getParameterMap() != null && request.getParameterMap().size() > 0){
                for(String key : request.getParameterMap().keySet()){
                    sb.append(",");
                    sb.append(key);
                    sb.append("|");
                    sb.append(request.getParameter(key));
                }
            }
            if(sb.length() > 0){
                sb.deleteCharAt(0);
            }
            log.error("param: " + sb.toString());
        }

        //未知错误
        return jsonResult;
    }


}
