package com.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableCaching
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySources(value = { @PropertySource(value = "classpath:/properties/${spring.profiles.active}.properties", ignoreResourceNotFound = true) })
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*@Autowired
    private Environment environment;
*/
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        // need to to wire up @value
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        /*provider.setPreAuthenticatedUserDetailsService(preAuthUserDetailsService());*/
        auth.authenticationProvider(provider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/", "/*.manifest", "/*.appcache", "/resources/**", "/healthMonitor**", "/cas", "/demo/**", "/h2/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                /*.authenticationEntryPoint(entryPoint())*/
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/secure/").permitAll()/*
                .anyRequest().authenticated()
                .and()
                .addFilter(requestEncryptedShibAuthenticationFilter())
                .addFilterBefore(redirectProfileFilter(), FilterSecurityInterceptor.class)*/;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("cache.xml"));
        cacheManagerFactoryBean.setShared(true);

        return cacheManagerFactoryBean;
    }
}
