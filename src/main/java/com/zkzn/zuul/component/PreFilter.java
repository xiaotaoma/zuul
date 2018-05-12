package com.zkzn.zuul.component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * 路由前过滤
 */
@Component
public class PreFilter extends ZuulFilter {

    /**
     * 过滤类型
     * pre 路由之前
     * routing 路由之时
     * post 路由之后
     * error 发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断是否需要过滤
     * @return
     * true 过滤
     * false 不过滤
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 具体过滤逻辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        Iterator<Map.Entry<String, String[]>> iterator = request.getParameterMap().entrySet().iterator();
        System.out.println("-------------------------- pre request params--------------------------");
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> entry = iterator.next();
            System.out.println("key:"+entry.getKey() + "\tvalue:"+entry.getValue()[0]);
        }
        System.out.println("-------------------------- pre request params--------------------------");
        String token = request.getParameter("token");
        System.out.println(token);
        if (token == null) {
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            HttpServletResponse response = currentContext.getResponse();
            try {
                response.getWriter().write("token is null");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
