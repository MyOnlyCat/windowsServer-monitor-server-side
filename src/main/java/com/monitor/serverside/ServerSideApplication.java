package com.monitor.serverside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 11609
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.monitor.serverside.server.clientFeignService")
public class ServerSideApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerSideApplication.class, args);
    }

}
