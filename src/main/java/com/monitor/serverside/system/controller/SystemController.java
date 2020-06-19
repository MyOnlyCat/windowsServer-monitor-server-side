package com.monitor.serverside.system.controller;

import com.monitor.serverside.api.vo.Result;
import com.monitor.serverside.system.sysInfo.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 七渣渣
 */
@RestController
@RequestMapping(value = "/api/system")
@Slf4j
public class SystemController {


    /**
     * 获取当前主机基本资源使用情况
     *
     * @return Server
     */
    @GetMapping(value = "/operationRatio")
    public Result<Object> getOperationRatio() {
        Server server = new Server();
        server.copyTo();
        System.out.println(server.getCpu().getTotal());
        return Result.ok(server);
    }
}
