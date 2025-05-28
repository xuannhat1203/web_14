package com.data.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.MessageSource; // Import thêm
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.validation.Validator; // Import thêm
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean; // Import thêm
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Locale;
import java.util.Map;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.data")
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = ObjectUtils.asMap(
                "cloud_name", "dfwuftmjf",
                "api_key", "659859734482993",
                "api_secret", "4VzlV5TT3rfz64oG7E1PTHkhzE4"
        );
        return new Cloudinary(config);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/javaWeb_session11?allowPublicKeyRetrieval=true&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(52428800); // 50 MB
        return multipartResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static/");
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:C:/Users/Admin/IdeaProjects/demo3/Session11/src/main/webapp/uploads/");
    }

    /**
     * Cấu hình đa ngôn ngữ - Locale Resolver sử dụng cookie để lưu lựa chọn ngôn ngữ của người dùng
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(new Locale("vi")); // Ngôn ngữ mặc định tiếng Việt
        cookieLocaleResolver.setCookieName("myLocaleCookie");
        cookieLocaleResolver.setCookieMaxAge(7 * 24 * 60 * 60); // 1 tuần
        return cookieLocaleResolver;
    }

    /**
     * Interceptor để thay đổi ngôn ngữ theo tham số "lang" trên URL
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang"); // Tham số dùng để đổi ngôn ngữ, ví dụ ?lang=en
        return interceptor;
    }

    /**
     * Đăng ký interceptor cho Spring MVC
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    /**
     * Cấu hình message source để load file properties thông báo đa ngôn ngữ
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // Đảm bảo "messages" là tên gốc của file properties của bạn (ví dụ: messages_en.properties, messages_vi.properties)
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    // -------------------------------------------------------------
    // THÊM ĐOẠN CODE NÀY VÀO ĐỂ FIX LỖI HIỂN THỊ THÔNG BÁO VALIDATION
    // -------------------------------------------------------------
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource()); // Đặt MessageSource mà bạn đã định nghĩa
        return validator;
    }
    // -------------------------------------------------------------
}