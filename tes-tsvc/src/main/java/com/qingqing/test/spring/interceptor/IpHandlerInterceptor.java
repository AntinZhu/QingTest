package com.qingqing.test.spring.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import com.qingqing.common.onoff.ISwitchDeterminer;
import com.qingqing.common.web.util.RequestExtract;
import com.qingqing.test.domain.user.TestUserIp;
import com.qingqing.test.manager.UserIpManager;
import com.qingqing.test.manager.WxNotifyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Set;

/**
 * Created by zhujianxing on 2019/7/8.
 */
public class IpHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(IpHandlerInterceptor.class);
    public static final String PAGE = "/logout";
    public static final Set<String> IGNORE_SET = Sets.newHashSet(
            PAGE,
            "/v1/utils/ip/up",
            "/v1/common/wx_notify",
            "/v1/common/wx_notify.json",
            "/v1/mock/invoke",
            "/v1/mock/invoke.json"
    );

    @Autowired
    private UserIpManager userIpManager;
    @Autowired
    private ISwitchDeterminer switchDeterminer;
    @Autowired
    private WxNotifyManager wxNotifyManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(switchDeterminer.isOn("all_user_allow", true)){
            return true;
        }

        String requestIp = RequestExtract.getServerIpByRequest((HttpServletRequest)request);
        if(!isIgnore(request.getRequestURI(), request.getContextPath())){
            TestUserIp userIp = userIpManager.getUserInfoIncTmp(requestIp);
            if(userIp == null){
                response.sendRedirect(request.getContextPath() + PAGE + "?requestUrl=" + URLEncoder.encode(request.getRequestURI().replace(request.getContextPath(), "")  + "?" + request.getQueryString(), "utf-8"));
                wxNotifyManager.selfNotify(buildNewIpFilterContent(requestIp));
            }
        }

        return true;
    }


    private String buildNewIpFilterContent(String newIp){
        JSONObject markdown = new JSONObject();
        markdown.put("content", "当日新IP被限制访问\n                >用户IP: <font color=\"comment\">" + newIp + "</font>\n");

        JSONObject content = new JSONObject();
        content.put("msgtype", "markdown");
        content.put("markdown", markdown);

        return content.toJSONString();
    }

    public boolean isIgnore(String requestUrl, String contentPath){
        for (String ignoreUrl : IGNORE_SET) {
            if(requestUrl.equals(contentPath + ignoreUrl)){
                return true;
            }
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String requestIp = RequestExtract.getServerIpByRequest((HttpServletRequest)httpServletRequest);
        if(modelAndView != null){
            modelAndView.addObject("qing_user_name", userIpManager.getUserNameByIp(requestIp));
            modelAndView.addObject("qing_user_ip", requestIp);
            modelAndView.addObject("qing_user", userIpManager.getUserInfoIncTmp(requestIp));
        }
    }
}
