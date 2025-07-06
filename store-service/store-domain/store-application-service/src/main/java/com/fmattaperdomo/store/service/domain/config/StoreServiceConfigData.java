package com.fmattaperdomo.store.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "store-service")
public class StoreServiceConfigData {
    private String storeApprovalRequestTopicName;
    private String storeApprovalResponseTopicName;
}
