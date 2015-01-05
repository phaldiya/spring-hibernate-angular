package com.learn.config;

import org.h2.server.web.WebServlet;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

@Configuration
/*@EnableAsync
@EnableScheduling*/
@ComponentScan(basePackages = { "com.learn" }, excludeFilters = @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION))
@WebListener()
@PropertySources(value = { @PropertySource(value = "classpath:/properties/${spring.profiles.active}.properties", ignoreResourceNotFound = true) })
public class WebConfig extends WebMvcConfigurationSupport implements WebApplicationInitializer {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        // need to to wire up @value
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/"); //.setCachePeriod(0);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebContentInterceptor interceptor = new WebContentInterceptor();
        interceptor.setCacheSeconds(0);
        interceptor.setUseExpiresHeader(true);
        interceptor.setUseCacheControlHeader(true);
        interceptor.setUseCacheControlNoStore(false);

        registry.addInterceptor(interceptor)
                .addPathPatterns("/")
                .addPathPatterns("/secure/")
                /*.addPathPatterns("/sit.manifest")*/
                /*.addPathPatterns("/profile")
                .addPathPatterns("/devtool*//**")*/
                .addPathPatterns("/api/**");
    }

    @Override
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = super.requestMappingHandlerMapping();
        handlerMapping.setUseSuffixPatternMatch(false);
        return handlerMapping;
    }

    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setOrder(2);
        return resolver;
    }

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean() {
        // this allow @async email services to still have access to SecurityContextHolder
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setTargetClass(SecurityContextHolder.class);
        bean.setTargetMethod("setStrategyName");
        bean.setArguments(new Object[] { "MODE_INHERITABLETHREADLOCAL" });
        return bean;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(10485760);
        return resolver;
    }

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        container.addListener(RequestContextListener.class);

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        String profile = rootContext.getEnvironment().getActiveProfiles()[0];

        if (profile.compareToIgnoreCase("local") == 0 || profile.compareToIgnoreCase("dev") == 0) {
            ServletRegistration.Dynamic h2Servlet = container.addServlet("h2console", new WebServlet());
            h2Servlet.setInitParameter("webAllowOthers", "true");
            h2Servlet.setLoadOnStartup(1);
            h2Servlet.addMapping("/h2/*");
        }
    }
}
