package com.monitor.serverside.server.controller;

import com.monitor.serverside.server.model.ServerAboutModel;
import com.monitor.serverside.system.service.SystemServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class ServerAboutController {

    private final SystemServiceI systemServiceI;

    public ServerAboutController(SystemServiceI systemServiceI) {
        this.systemServiceI = systemServiceI;
    }

    /**
     * 返回服务器的基本信息
     * @return
     */
    @PostMapping(value = "/report/serverAbout")
    public ServerAboutModel reportServerAbout() {
        return systemServiceI.getServiceAbout();
    }

}
