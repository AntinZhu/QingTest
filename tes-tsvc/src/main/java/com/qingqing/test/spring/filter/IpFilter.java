package com.qingqing.test.spring.filter;

import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.util.TimeUtil;
import com.qingqing.common.web.util.RequestExtract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhujianxing on 2019/2/13.
 */
public class IpFilter implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(IpFilter.class);

    private String today;
    private Set<String> ipSet;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        today = TimeUtil.dateToMonthDay(new Date());
        ipSet = new HashSet<>();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestIp = RequestExtract.getServerIpByRequest((HttpServletRequest)servletRequest);

        String requestDay = TimeUtil.dateToMonthDay(new Date());
        if(!today.equals(requestDay)){
            ipSet = new HashSet<>();
            today = requestDay;

            addNewIp(requestIp);
        }else{
            if(!ipSet.contains(requestIp)){
                addNewIp(requestIp);
            }
        }

        try{
            MDC.put("ip", requestIp);
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            MDC.clear();
        }
    }

    private void addNewIp(String ip){
        ipSet.add(ip);
        logger.warn("request from ip :" + ip + ", full in today:" + JsonUtil.format(ipSet));
    }

    @Override
    public void destroy() {

    }
}
