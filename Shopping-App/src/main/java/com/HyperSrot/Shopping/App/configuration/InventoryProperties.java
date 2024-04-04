package com.HyperSrot.Shopping.App.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.inventory")
@Data
public class InventoryProperties {
    private int initialQuantity;
    private int price;
}
