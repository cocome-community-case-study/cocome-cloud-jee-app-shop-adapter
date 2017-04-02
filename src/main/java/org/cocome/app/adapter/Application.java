package org.cocome.app.adapter;
import org.cocome.cloud.logic.registry.client.ApplicationHelper;
import org.cocome.cloud.logic.registry.client.IApplicationHelper;
import org.cocome.cloud.shop.inventory.connection.CustomerQuery;
import org.cocome.cloud.shop.inventory.connection.EnterpriseQuery;
import org.cocome.cloud.shop.inventory.connection.LoginQuery;
import org.cocome.cloud.shop.inventory.connection.StoreQuery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan("org.cocome")
public class Application {
	
	
	@Bean
	IApplicationHelper createApplicationHelper()
	{
		return new ApplicationHelper();
	}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}