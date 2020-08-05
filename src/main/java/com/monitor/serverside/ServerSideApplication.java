package com.monitor.serverside;

import com.monitor.serverside.dynamicTask.controller.DynamicTaskController;
import com.monitor.serverside.util.BeanContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 11609
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.monitor.serverside.server.service.feignService")
public class ServerSideApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerSideApplication.class, args);
        DynamicTaskController bean = BeanContext.getApplicationContext().getBean(DynamicTaskController.class);
        bean.startCron();
    }

}
