package com.edureka.apigatewaywithoutoauth2.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class AddRequestHeaderFilter extends ZuulFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddRequestHeaderFilter.class);
    @Override
    public String filterType() {
        return "pre";
    } // post, error, route

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // can have custom condition to apply filter or not
        // senderType is queryParam in the request
//        RequestContext.getCurrentContext().getParameter("senderType"); == mobile-client
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // add starttime to context as Instant.now in prefilter
        // and then calculate time in post filter
        LOGGER.info("*********************");
        System.out.println("*********************");
        System.out.println("***************** called the filter-run");
        RequestContext currentContext = RequestContext.getCurrentContext();
        currentContext.set("starttime", Instant.now());
//        currentContext.addZuulRequestHeader("x-location", "USA");
//        currentContext.addZuulRequestHeader("x-starttime", Instant.now());
//        currentContext.addZuulRequestHeader("starttime", Instant.now().toString());
        return null;
    }
}
