package com.monitor.serverside.server.clientFeignService;

import com.monitor.serverside.server.vo.ServerReportVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 11609
 */
@FeignClient(name = "client-about-server", url = "http://127.0.0.1:7070/")
public interface IClientFeignService {

    /**
     * 上报自己的服务器信息
     */
    @PostMapping(value = "/api/server/serverSave", consumes = "application/json")
    void saveThisServerAndStopTask(ServerReportVo serverReportVo);

}
