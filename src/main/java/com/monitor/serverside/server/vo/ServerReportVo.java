package com.monitor.serverside.server.vo;

import cn.hutool.core.date.DateUtil;
import com.monitor.serverside.util.ServerUtil;
import lombok.Data;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.Date;
import java.util.Properties;

/**
 * @author 七渣渣
 * 服务上报详情
 */
@Data
public class ServerReportVo {

    /**
     * 服务器IP
     */
    private final String ip = ServerUtil.getIp();

    /**
     * 服务器操作系统
     */
    private String osName;

    /**
     * 系统架构
     */
    private String osArch;

    /**
     * 项目路径
     */
    private String projectDir;

    /**
     * 服务上报时间
     */
    private final Date reportTime = DateUtil.date();

    public void setInfo() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        Properties props = System.getProperties();
        this.osName = props.getProperty("os.name");
        this.osArch = props.getProperty("os.arch");
        this.projectDir = props.getProperty("user.dir");
    }



    @Override
    public String toString() {
        return "ServerReportVo{" +
                "ip='" + ip + '\'' +
                ", osName='" + osName + '\'' +
                ", reportTime=" + reportTime +
                '}';
    }

}
