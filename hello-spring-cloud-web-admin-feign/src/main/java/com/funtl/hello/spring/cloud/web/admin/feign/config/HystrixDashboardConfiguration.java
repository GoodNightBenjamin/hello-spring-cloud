package com.funtl.hello.spring.cloud.web.admin.feign.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixDashboardConfiguration {


    // 以下方式是springboot注入servlet并进行配置的方式。
    @Bean
    public ServletRegistrationBean getServlet() {

        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);

        /**
         * 当值为0或者大于0时，表示容器在应用启动时就加载这个servlet；
         *
         * 当是一个负数时或者没有指定时，则指示容器在该servlet被选择时才加载。
         *
         * 正数的值越小，启动该servlet的优先级越高。
         */
        registrationBean.setLoadOnStartup(1);  // servlet启动顺序

        registrationBean.addUrlMappings("/hystrix.stream");        // servlet访问路径
        registrationBean.setName("HystrixMetricsStreamServlet");   // servlet名称
        return registrationBean;
    }

}
