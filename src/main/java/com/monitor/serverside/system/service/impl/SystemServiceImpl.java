package com.monitor.serverside.system.service.impl;

import com.monitor.serverside.server.model.ServerAboutModel;
import com.monitor.serverside.system.service.SystemServiceI;
import com.monitor.serverside.util.ServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * @author 11609
 */
@Slf4j
@Service
public class SystemServiceImpl implements SystemServiceI {

    @Override
    public ServerAboutModel getServiceAbout() {

        ServerAboutModel serverAboutModel = new ServerAboutModel();

        Properties props = System.getProperties();
        // 主机名称设置
        serverAboutModel.setComputerName(ServerUtil.getHostName());
        // ip设置
        serverAboutModel.setServerIp(ServerUtil.getIp());
        // 操作系统设置
        serverAboutModel.setOsName(props.getProperty("os.name"));
        // 服务器系统架构设置
        serverAboutModel.setOsArch(props.getProperty("os.arch"));
        // 服务器Java版本设置
        serverAboutModel.setJavaVersion(props.getProperty("java.version"));
        // 项目路径
        serverAboutModel.setProjectDir(props.getProperty("user.dir"));

        return serverAboutModel;

    }

}
