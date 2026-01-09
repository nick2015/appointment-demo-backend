package com.nick.TaroDemo.interceptor;

import com.github.benmanes.caffeine.cache.Cache;
import com.nick.TaroDemo.NoAuthCheck;
import com.nick.TaroDemo.entity.UserInfo;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private static final String TOKEN = "X-Auth-Token";

    @Autowired
    private Cache<String, UserInfo> tokenCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();
        String authToken = request.getHeader(TOKEN);

        log.info("请求地址是：{}", url);
        log.info("请求头token为：{}", authToken);

        Map<String, String[]> map = request.getParameterMap();
        Iterator<Map.Entry<String, String[]>> iterator = map.entrySet().iterator();
        StringBuilder buffer = new StringBuilder();
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> entry = iterator.next();
            String[] value = entry.getValue();
            buffer.append(entry.getKey()).append("&").append(value[0]);
        }
        log.info("请求头参数为{}", buffer);
        log.info("请求类型为" + request.getMethod());

        // Annotation check
        if (handler instanceof HandlerMethod handlerMethod) {
            Method method = handlerMethod.getMethod();
            if (method.isAnnotationPresent(NoAuthCheck.class)) {
                return true;
            }
            if (StringUtils.isBlank(authToken)){
                this.setAuthResponse(response);
                return false;
            }

            // get user cache
            UserInfo userInfo = tokenCache.getIfPresent(authToken);
            if (null == userInfo){
                this.setAuthResponse(response);
                return false;
            }

            // reset expire time / currently only support single thread
            tokenCache.invalidate(authToken);
            tokenCache.put(authToken, userInfo);
            return true;
        }
        return true;
    }

    private void setAuthResponse(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append("User not authticated");
        }catch (Exception e){
            log.error("System error", e);
        }finally {
            if (out != null){
                out.close();
            }
        }
    }
}
