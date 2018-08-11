package com.acfun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo class 启动类
 *
 *
 * @author an
 * @date 2018/8/9
 */
@SpringBootApplication
@ServletComponentScan
public class Application {

//    @Bean
//    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
//        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
//        List<HttpMessageConverter> mappingJackson2HttpMessageConverters = new ArrayList<>();
//        mappingJackson2HttpMessageConverters.add(mappingJackson2HttpMessageConverter);
//        requestMappingHandlerAdapter.setMessageConverters(mappingJackson2HttpMessageConverters);
//        return requestMappingHandlerAdapter;
//    }
//
//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        List<MediaType> list = new ArrayList<>();
//
//        list.add(new MediaType("text/html;charset=UTF-8"));
//        list.add(new MediaType("application/x-www-form-urlencoded;charset=UTF-8"));
//        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
//        return mappingJackson2HttpMessageConverter;
//    }

    @Bean
    ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //如果池中的实际线程数小于corePoolSize,无论是否其中有空闲的线程，都会给新的任务产生新的线程
        threadPoolTaskExecutor.setCorePoolSize(5);
        //连接池中保留的最大连接数。Default: 15 maxPoolSize
        threadPoolTaskExecutor.setMaxPoolSize(10);
        //queueCapacity 线程池所使用的缓冲队列
        threadPoolTaskExecutor.setQueueCapacity(25);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
