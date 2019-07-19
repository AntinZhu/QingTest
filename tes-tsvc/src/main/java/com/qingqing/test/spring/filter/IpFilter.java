package com.qingqing.test.spring.filter;

import com.alibaba.fastjson.JSONObject;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.util.TimeUtil;
import com.qingqing.common.web.util.RequestExtract;
import com.qingqing.test.bean.schedule.QingScheduleType;
import com.qingqing.test.bean.schedule.QingScheduleable;
import com.qingqing.test.manager.UserIpManager;
import com.qingqing.test.manager.WxNotifyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Component
public class IpFilter implements Filter, QingScheduleable {
    private final static Logger logger = LoggerFactory.getLogger(IpFilter.class);

    private String today;
    private Set<String> ipSet;
    private static ThreadLocal<String> requestUserIp = new ThreadLocal<>();

    @Autowired
    private WxNotifyManager wxNotifyManager;
    @Autowired
    private UserIpManager userIpManager;

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
            requestUserIp.set(requestIp);
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            MDC.clear();
            requestUserIp.remove();
        }
    }

    private void addNewIp(String ip){
        ipSet.add(ip);
        logger.warn("request from ip :" + ip + ", full in today:" + JsonUtil.format(ipSet));
        wxNotify(ip);
    }

    public void wxNotify(String newIp){
        wxNotifyManager.selfNotify(buildNewIpNotifyContent(newIp));
    }

    private String buildNewIpNotifyContent(String newIp){
        JSONObject markdown = new JSONObject();
        markdown.put("content", "当日新IP访问\n                >用户IP: <font color=\"comment\">" + newIp + "</font>\n                >用户名: <font color=\"comment\">" + userIpManager.getUserNameByIp(newIp) + "</font>\n                >访问总人数: <font color=\"comment\">" + ipSet.size() + "</font> ");

        JSONObject content = new JSONObject();
        content.put("msgtype", "markdown");
        content.put("markdown", markdown);

        return content.toJSONString();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doSchedule() {
        StringBuilder allUserName = new StringBuilder();
        allUserName.append("[");
        for(String userIp : ipSet){
            allUserName.append(userIpManager.getUserNameByIp(userIp)).append(", ");
        }
        allUserName.append("]");

        JSONObject markdown = new JSONObject();
        markdown.put("content", "当日访问总结\n                >使用的用户: <font color=\"comment\">" + allUserName.toString() + "</font>\n                >访问总人数: <font color=\"comment\">" + ipSet.size() + "</font> ");

        JSONObject content = new JSONObject();
        content.put("msgtype", "markdown");
        content.put("markdown", markdown);

        wxNotifyManager.selfNotify(content.toJSONString());
    }

    @Override
    public QingScheduleType[] getScheduleTypes() {
        return new QingScheduleType[]{QingScheduleType.daily};
    }

    public static String getRequestUserIp(){
        return requestUserIp.get();
    }
}
