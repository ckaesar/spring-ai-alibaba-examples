package com.alibaba.cloud.ai.toolcall.config;

import com.alibaba.cloud.ai.toolcall.component.RandNumberTools;
import com.alibaba.cloud.ai.toolcall.service.GetRandNumberService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(GetRandNumberService.class)
public class RandNumberToolCallAutoConfiguration {

    @Bean
    public RandNumberTools randNumberTools(GetRandNumberService service) {
        return new RandNumberTools(service);
    }

}
