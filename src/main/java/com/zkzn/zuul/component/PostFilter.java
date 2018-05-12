package com.zkzn.zuul.component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

@Component
public class PostFilter extends ZuulFilter {
    /**
     * 路由后过滤
     * @return
     */
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        Iterator<Map.Entry<String, String[]>> iterator = request.getParameterMap().entrySet().iterator();
        System.out.println("-------------------------- post request params--------------------------");
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> entry = iterator.next();
            System.out.println("key:"+entry.getKey() + "\tvalue:"+entry.getValue()[0]);
        }
        System.out.println("-------------------------- post request params--------------------------");
        return null;
    }
}
