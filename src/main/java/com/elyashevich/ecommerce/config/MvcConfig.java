package com.elyashevich.ecommerce.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import static com.elyashevich.ecommerce.util.TemplateResolverUtil.*;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.elyashevich.ecommerce")
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        var templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix(PREFIX);
        templateResolver.setSuffix(SUFFIX);
        templateResolver.setCacheable(CACHEABLE);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        var templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(this.templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(final ViewResolverRegistry registry) {
        var resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(this.templateEngine());
        registry.viewResolver(resolver);
    }
}