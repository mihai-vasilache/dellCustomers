package com.dell

import com.dell.customers.generated.client.ApiClient
import feign.form.spring.SpringFormEncoder
import org.springframework.beans.factory.ObjectFactory
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder
import org.springframework.cloud.openfeign.support.SpringDecoder
import org.springframework.cloud.openfeign.support.SpringEncoder

class FeignApiTestClientGenerator {

    private ObjectFactory<HttpMessageConverters> messageConverters

    FeignApiTestClientGenerator(ObjectFactory<HttpMessageConverters> messageConverters) {
        this.messageConverters = messageConverters
    }

    def <T extends ApiClient.Api> T generate(Class<T> type, String url) {
        ApiClient apiClient = new ApiClient()
        apiClient.getFeignBuilder()
                .encoder(new SpringFormEncoder(new SpringEncoder(messageConverters)))
                .decoder(new ResponseEntityDecoder(new SpringDecoder(messageConverters)))
        apiClient.setBasePath(url)
        return apiClient.buildClient(type)
    }
}
