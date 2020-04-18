package ${basePackage}.config;

import com.qingqing.common.web.filter.Log4jFilter;
import com.qingqing.common.web.filter.ServerAuthorizationFilter;
import com.qingqing.common.web.filter.SetCharacterEncodingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/**
* Created by ${user!'test-api'} on ${date}
*/
@Configuration
public class WebFilterConfig {

    @Bean
    public FilterRegistrationBean setCharacterEncodingFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("setCharacterEncodingFilter");
        SetCharacterEncodingFilter greetingFilter = new SetCharacterEncodingFilter();
        registrationBean.setFilter(greetingFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(0);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean hiddenHttpMethodFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("hiddenHttpMethodFilter");
        HiddenHttpMethodFilter greetingFilter = new HiddenHttpMethodFilter();
        registrationBean.setFilter(greetingFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(10);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean serverAuthorizationFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("serverAuthorizationFilter");
        ServerAuthorizationFilter serverAuthorizationFilter = new ServerAuthorizationFilter();
        registrationBean.setFilter(serverAuthorizationFilter);
        registrationBean.addUrlPatterns("/api/pi/*");
        registrationBean.setOrder(20);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean log4jFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("log4jFilter");
        Log4jFilter greetingFilter = new Log4jFilter();
        registrationBean.setFilter(greetingFilter);
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(50);
        return registrationBean;
    }
}
