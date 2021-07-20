package cn.ag.channel.interceptor;

//import jdk.nashorn.internal.objects.Global;
import cn.ag.channel.exception.MyEumException;
import cn.ag.channel.jsonResult.JsonResult;
import cn.ag.channel.model.User;
import cn.ag.channel.util.JwtUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        Map<Object, Object> map = new HashMap<>();
        //如果是OPTIONS请求 直接放行
        String method = request.getMethod();
        JsonResult jsonResult;
        try {
            if(method.equals("OPTIONS")){
                return  true;
            }
            //从请求中获取令牌
            String jwtToken = request.getHeader("Auth");
            if(String.valueOf(jwtToken).equals("null")){
                throw new SignatureException("令牌不合法");
            }
            //验证token
            String userId  = JwtUtils.verifyJwtToken(jwtToken);
            //验证成功后,如果令牌有效时间<=5分钟,则签发新的令牌,刷新令牌时间
            if(userId != null){
                JwtUtils.refreshToken(userId);
                return  true ;
            }else{
                jsonResult = new JsonResult(new ArrayList<>(), MyEumException.TOKEN_TIME_OUT.getCode(),MyEumException.TOKEN_TIME_OUT.getMessage());
            }
        }catch(SignatureException e){
            e.printStackTrace();
            jsonResult = new JsonResult(new ArrayList<>(), MyEumException.TOKEN_TIME_OUT.getCode(),MyEumException.TOKEN_TIME_OUT.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(new ArrayList<>(), MyEumException.TOKEN_GET_FAIL.getCode(),MyEumException.TOKEN_GET_FAIL.getMessage());
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(JSONObject.toJSONString(jsonResult));
        return false;
    }
}
