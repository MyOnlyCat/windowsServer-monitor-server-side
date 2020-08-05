package com.monitor.serverside.dynamicTask.service;

import com.monitor.serverside.server.service.feignService.IClientFeignService;
import com.monitor.serverside.server.vo.ServerReportVo;
import com.monitor.serverside.util.BeanContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 七渣渣
 * 定时任务类
 */
@Slf4j
public class ServerReportRunnable implements Runnable{


    @Override
    public void run() {
        ServerReportVo serverReportVo = new ServerReportVo();
        IClientFeignService clientFeignService = BeanContext.getApplicationContext().getBean(IClientFeignService.class);
        clientFeignService.saveThisServerAndStopTask(serverReportVo);
        log.info("服务上报, {}", serverReportVo.toString());
    }

}
