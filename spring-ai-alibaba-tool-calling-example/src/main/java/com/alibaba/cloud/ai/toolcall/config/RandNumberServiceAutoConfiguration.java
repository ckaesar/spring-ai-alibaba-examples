package com.alibaba.cloud.ai.toolcall.config;

import com.alibaba.cloud.ai.toolcall.service.GetRandNumberService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

@Configuration
@ConditionalOnClass({GetRandNumberService.class})
//@ConditionalOnProperty(prefix = "spring.ai.alibaba.toolcalling.time", name = "enabled", havingValue = "true")
public class RandNumberServiceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @Description("Get several random numbers in a specified range.")
    public GetRandNumberService getRandNumberService() {
        return new GetRandNumberService();
    }

}
