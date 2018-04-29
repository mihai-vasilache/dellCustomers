package com.dell

import org.springframework.beans.factory.ObjectFactory
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiTestClientBeanConfig {

    @Bean
    FeignApiTestClientGenerator feignApiTestClientGenerator(ObjectFactory<HttpMessageConverters> messageConverters) {
        new FeignApiTestClientGenerator(messageConverters)
    }
}
