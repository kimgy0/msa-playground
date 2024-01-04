package com.example.zuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class ZuulLoggingFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre"; // 사전 필터
    }

    @Override
    public int filterOrder() {
        return 1; // 순서
    }

    @Override
    public boolean shouldFilter() {
        return true; // 필터를 쓰지 않는다 : false / 필터를 쓴다 : true
    }

    @Override
    public Object run() throws ZuulException {
        log.info("*************** printing logs start");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("*************** {}" , request.getRequestURI());
        log.info("*************** printing logs end");
        return null;
    }
}
