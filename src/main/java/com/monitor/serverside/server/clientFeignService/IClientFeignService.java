package com.monitor.serverside.server.clientFeignService;

import com.monitor.serverside.server.vo.ServerReportVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 11609
 */
@FeignClient(name = "client-about-server", url = "http://192.168.0.134:7070/")
public interface IClientFeignService {

    /**
     * 上报自己的服务器信息,上报成功的话客户端会发生停止定时任务命令
     */
    @PostMapping(value = "/api/server/serverSave", consumes = "application/json")
    void saveThisServerAndStopTask(ServerReportVo serverReportVo);

}
