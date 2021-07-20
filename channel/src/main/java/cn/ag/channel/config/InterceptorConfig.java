package cn.ag.channel.config;

import cn.ag.channel.interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Value("${interceptor.excludePath}")
    private String excludePath;

    /**
     * 注册拦截器
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**");

        List<String> list = Arrays.asList(excludePath.split(","));
        //不需要拦截的地址，如登录接口
        list.forEach(str -> interceptorRegistration.excludePathPatterns(str));

    }
}
