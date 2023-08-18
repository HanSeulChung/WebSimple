package com.example.websimple.config;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
// @Component
// filter가 여러개이거나 설정을 자유롭게 하기 위해서는
// Component로 직접적으로 주는것보다 config 자바 파일을 만드는 것이 더 좋다.
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // 외부에서 Filter를 통해 들어와 Controller로 처리가 되고 다시 filter를 통해서 외부로 감
        // 흐름: 외부 -> filter -> controller에서 처리 -> filter -> 외부
        // -> controller에서 처리되고 다시 filter로 오는 부분을 하기 위해서 filter chain을 이용, 이어주어야 한다.
        // filter는 여러개가 있을 수 있으므로 filter를 chain으로 이어주는 것을 반복해야할 수 도 있다.
        log.info("Hello filter: " + Thread.currentThread());
        chain.doFilter(request, response);
        log.info("By filter: " + Thread.currentThread());
    }
}
