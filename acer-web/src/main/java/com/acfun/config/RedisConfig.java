/*
 * Copyright (c) 2017. www.servingcloud.com Inc. All rights reserved.
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.acfun.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author hsky www.servingcloud.com
 * @since 2017年6月5日 下午12:32:43
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${redis.redisson-path}")
    private String redissonPath;

    @Bean(destroyMethod = "shutdown", value = "redissonClient")
    RedissonClient redisson() {
        try {
            Config config = Config.fromYAML(new ClassPathResource(redissonPath).getInputStream());
            return Redisson.create(config);
        } catch (IOException e) {
            LOGGER.error("Cannot configure redisson client,The redisson.yml " +
                    "file does not exist or is not in the correct format", e);
        }
        return null;
    }

    @Bean("cacheManager")
    public CacheManager cacheManager(RedissonClient redissonClient) {
        return new RedissonSpringCacheManager(redissonClient, "classpath:/redisson-cache-config.json");
    }
}