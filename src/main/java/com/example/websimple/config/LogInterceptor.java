package com.example.websimple.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("preHandele LogInterceptor : " + Thread.currentThread());
        log.info("preHandle handler : " + handler);
        return true; // 계속 요청하고 싶으면 true 여기서 끝내고싶으면 false
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("postHandle LogInterceptor : " + Thread.currentThread());
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        log.info("afterCompletion LogInterceptor : " + Thread.currentThread());

        if (ex != null) { // 핸들러에서 발생한 ex이 터질 수 도 있음
            log.error("afterCompletion exception " + ex.getMessage());
        }
    }
}
