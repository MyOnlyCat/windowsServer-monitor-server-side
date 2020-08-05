package com.monitor.serverside.server.model;

import lombok.Data;

import java.io.Serializable;

/**
 * (ServerAbout)实体类
 *
 * @author makejava
 * @since 2020-08-05 18:30:53
 */
@Data
public class ServerAboutModel implements Serializable {
    private static final long serialVersionUID = -56890038471354918L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 服务器IP
    */
    private String serverIp;
    /**
    * 服务器操作系统
    */
    private String osName;
    /**
    * 服务器名称
    */
    private String computerName;
    /**
    * 服务器系统架构
    */
    private String osArch;
    /**
    * 服务器Java版本
    */
    private String javaVersion;
    /**
    * 项目路径
    */
    private String projectDir;
}