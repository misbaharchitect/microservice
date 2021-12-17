package com.edureka.apigatewaywithoutoauth2.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class PostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    } // post, error

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // can have custom condition to apply filter or not
        // senderType is queryParam in the request
//        RequestContext.getCurrentContext().getParameter("senderType");
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // add starttime to context as Instant.now in prefilter
        // and then calculate time in post filter
        System.out.println("*********************");
        System.out.println("*********************");
        System.out.println("***************** called the filter-run");
        RequestContext currentContext = RequestContext.getCurrentContext();
        Instant starttime = (Instant) currentContext.get("starttime");

        long requestDuration = ChronoUnit.MILLIS.between(starttime, Instant.now());
        System.out.println("request time taken(ms): " + requestDuration);
//        currentContext.addZuulRequestHeader("x-location", "USA");
//        currentContext.addZuulRequestHeader("x-starttime", Instant.now());
//        currentContext.addZuulRequestHeader("starttime", Instant.now().toString());
        return null;
    }
}
