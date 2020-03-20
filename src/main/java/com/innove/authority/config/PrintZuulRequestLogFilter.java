package com.innove.authority.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;

@Component
public class PrintZuulRequestLogFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(PrintZuulRequestLogFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;//要打印返回信息，必须得用"post"
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
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            StringBuilder log = new StringBuilder();
            HttpServletRequest request = ctx.getRequest();
            InputStream in = request.getInputStream();
            String reqBbody = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            // 打印请求方法，路径
            Map<String, String[]> map = request.getParameterMap();
            // 打印请求url参数
            StringBuilder sb = new StringBuilder();
            if (map != null) {
                for (Map.Entry<String, String[]> entry : map.entrySet()) {
                    sb.append("[" + entry.getKey() + "=" + printArray(entry.getValue()) + "]");
                }
            }
            // 打印response
            InputStream out = ctx.getResponseDataStream();
            String outBody = StreamUtils.copyToString(out, Charset.forName("UTF-8"));
            log.append("\n-----------------------------start-----------------------------");
            log.append(String.format("%nURL：%s?{%s}",request.getRequestURL(),sb.toString()));
            log.append(String.format("%nMethod：%s",request.getMethod()));
            log.append(String.format("%nIP：%s",request.getRemoteAddr()));
            log.append(String.format("%n请求报文：%s", reqBbody));
            log.append(String.format("%n响应报文：%s", outBody));
            log.append("\n-----------------------------end-----------------------------");
            logger.info(log.toString());
            ctx.setResponseBody(outBody);//重要！！！

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }



    String printArray(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
