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
     * 服务上报时间
     */
    private final Date reportTime = DateUtil.date();

    @Override
    public String toString() {
        return "ServerReportVo{" +
                "ip='" + ip + '\'' +
                ", reportTime=" + reportTime +
                '}';
    }
}
