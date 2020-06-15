package com.monitor.serverside.server.vo;

import cn.hutool.core.date.DateUtil;
import com.monitor.serverside.util.ServerUtil;
import lombok.Data;

import java.util.Date;

/**
 * @author 七渣渣
 * 服务上报详情
 */
public class ServerReportVo {

    /**
     * 服务器IP
     */
    private final String ip = ServerUtil.getIp();

    /**
     * 服务器操作系统
     */
    private final String osName = ServerUtil.getOsName();

    /**
     * 服务上报时间
     */
    private final Date reportTime = DateUtil.date();

    public String getIp() {
        return ip;
    }

    public String getOsName() {
        return osName;
    }

    public Date getReportTime() {
        return reportTime;
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
